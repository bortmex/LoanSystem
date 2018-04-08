package ru.javaproject.loansystem.web.arealizationonlywithservlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import ru.javaproject.loansystem.AuthorizedUser;
import ru.javaproject.loansystem.model.*;
import ru.javaproject.loansystem.util.PartnerUtil;
import ru.javaproject.loansystem.util.UsersUtil;
import ru.javaproject.loansystem.web.creaditapplicationlistproduct.CreditApplicationListProductRestController;
import ru.javaproject.loansystem.web.creditapplication.CreditApplicationRestController;
import ru.javaproject.loansystem.web.product.ProductRestController;
import ru.javaproject.loansystem.web.user.ProfileRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class ServletForUser extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(ServletForUser.class);

    private ProductRestController productRestController;
    private ProfileRestController profileRestController;
    private CreditApplicationRestController creditapplicationRestController;
    private CreditApplicationListProductRestController creditApplicationListProductRestController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        WebApplicationContext springContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());

        productRestController = springContext.getBean(ProductRestController.class);
        profileRestController = springContext.getBean(ProfileRestController.class);
        creditapplicationRestController = springContext.getBean(CreditApplicationRestController.class);
        creditApplicationListProductRestController = springContext.getBean(CreditApplicationListProductRestController.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        LOG.debug("redirect 'method get' for User {}", AuthorizedUser.id());
        String action = req.getParameter("action");
        req.setAttribute("userId", AuthorizedUser.id());
        if (action == null) {
            List<User> userList = profileRestController.getAll();
            LOG.info("getAll Users");
            if (!UsersUtil.isPartnerWithList(AuthorizedUser.id(), userList)) {
                List<User> dsf1 = userList.stream().filter(u -> u.getRoles().contains(Role.ROLE_PARTNER)).collect(Collectors.toList());
                req.setAttribute("partners", dsf1);
                LOG.info("getAll for User {}", AuthorizedUser.id());
                req.setAttribute("creditapplication", creditapplicationRestController.getAllForUsersId(AuthorizedUser.id()));
                req.getRequestDispatcher("partnerlist.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("createcreditapplication.jsp").forward(req, resp);
            }

        } else if (action.equals("see")) {
            String use = req.getParameter("use");
            if (use == null) {
                req.setAttribute("products", PartnerUtil.getProductListsFilteredByOnePartner((List<Product>) productRestController.getAll(), getReqInt(req, "id")));
                User partner = profileRestController.get(getReqInt(req, "id"));
                req.setAttribute("partnerId", partner.getId());
                req.setAttribute("partnerName", partner.getName());
                LOG.info("method get Partner {}", partner);
                req.getRequestDispatcher("product.jsp").forward(req, resp);
            } else if ("create".equals(use) || "update".equals(use)) {
                CreditApplication creditApplication = "create".equals(use) ? new CreditApplication("", null, LocalDateTime.now(), "", null) :
                        creditapplicationRestController.get(getReqInt(req, "idcrapp"), AuthorizedUser.id());

                LOG.info("method Create/Update creditapplication {}", creditApplication);
                req.setAttribute("creditApplication", creditApplication);
                List<Product> listProd = PartnerUtil.getProductListsFilteredByOnePartner((List<Product>) productRestController.getAll(getReqInt(req, "id")), getReqInt(req, "id"));
                req.setAttribute("products", listProd);
                req.setAttribute("productsTrue", creditApplication.getProduct());
                req.setAttribute("partnerId", listProd.iterator().next().getUser().getId());
                req.getRequestDispatcher("createcreditapplication.jsp").forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, DateTimeParseException {
        req.setCharacterEncoding("UTF-8");
        LOG.debug("redirect 'method post' for User {}", AuthorizedUser.id());
        req.setAttribute("userId", AuthorizedUser.id());
        String action = req.getParameter("action");
        if (action == null) {
            final CreditApplication creditApplication = new CreditApplication(
                    req.getParameter("fio"),
                    LocalDate.parse(req.getParameter("dateBirth")),
                    LocalDateTime.now(),
                    req.getParameter("phoneNumber"),
                    getReqInt(req, "anInitialFee")
            );

            Set<Product> productList = new HashSet<>();
            String parnerId = req.getParameter("partId");

            List<Product> partnerAllProduct = PartnerUtil.getProductListsFilteredByOnePartner((List<Product>) productRestController.getAll(
                    Integer.parseInt(parnerId)), Integer.parseInt(parnerId));
            for (Product product : partnerAllProduct) {
                String productId = req.getParameter("product" + product.getId());
                if (productId == null) continue;
                if (productId.equals("on"))
                    productList.add(product);
            }
            LOG.info("getAll product for User {}", productList);
            if (req.getParameter("idcrapp").isEmpty()) {
                creditapplicationRestController.create(creditApplication);
                LOG.info("Create {} Credit Application", creditApplication);
                for (Product product : productList) {
                    creditApplicationListProductRestController.create(new CreditApplicationListProduct(creditApplication.getId(), product.getId()));
                }
            } /*else {
                int idcrapp = getReqInt(req, "idcrapp");
                creditapplicationRestController.update(creditApplication, idcrapp);
                LOG.info("Credit Application", creditApplication);
                creditApplicationListProductRestController.delete(idcrapp);
                for (Product product : productList) {
                    creditApplicationListProductRestController.create(new CreditApplicationListProduct(idcrapp, product.getId()));
                }
            }*/

            req.setAttribute("partnerId", profileRestController.get(getReqInt(req, "partId")).getId());
            req.setAttribute("partnerName", profileRestController.get(getReqInt(req, "partId")).getName());

            List<User> dsf1 = profileRestController.getAll().stream().filter(u -> u.getRoles().contains(Role.ROLE_PARTNER)).collect(Collectors.toList());
            LOG.info("getAll Partner", dsf1);
            req.setAttribute("partners", dsf1);
            req.setAttribute("creditapplication", creditapplicationRestController.getAllForUsersId(AuthorizedUser.id()));

            req.getRequestDispatcher("partnerlist.jsp").forward(req, resp);
        }
    }
    /**
     * Get param name with request and returt int
     */
    private int getReqInt(HttpServletRequest request, String name) {
        String paramId = Objects.requireNonNull(request.getParameter(name));
        return Integer.valueOf(paramId);
    }
}
