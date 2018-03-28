package ru.javaproject.loansystem.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javaproject.loansystem.AuthorizedUser;
import ru.javaproject.loansystem.model.CreditApplication;
import ru.javaproject.loansystem.model.Product;
import ru.javaproject.loansystem.model.User;
import ru.javaproject.loansystem.util.PartnerListUtil;
import ru.javaproject.loansystem.util.UsersUtil;
import ru.javaproject.loansystem.web.creditapplication.CreditApplicationRestController;
import ru.javaproject.loansystem.web.product.ProductRestController;
import ru.javaproject.loansystem.web.user.ProfileRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class ServletForPartner extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(ServletForPartner.class);

    private ConfigurableApplicationContext springContext;
    private ProfileRestController profileRestController;
    private ProductRestController productRestController;
    private CreditApplicationRestController creditapplicationRestController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml");
        profileRestController = springContext.getBean(ProfileRestController.class);
        creditapplicationRestController = springContext.getBean(CreditApplicationRestController.class);
        productRestController = springContext.getBean(ProductRestController.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("redirect 'method get' for Partner {}", AuthorizedUser.id());
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if (action == null) {
            req.setAttribute("creditapplication", PartnerListUtil.getListsOfApplicationsForOnePartner((List<CreditApplication>) creditapplicationRestController.getAll(),AuthorizedUser.id()));
            LOG.info("getAllcreditapplication");
            req.setAttribute("partnerName", profileRestController.get(AuthorizedUser.id()).getName());
            req.setAttribute("products", productRestController.getAll(AuthorizedUser.id()));

            req.getRequestDispatcher("showCreditAppListAndProductListForPartner.jsp").forward(req, resp);
        } else if ("create".equals(action)) {
            final Product product = "create".equals(action) ? new Product("", 0, "")
                                                            : productRestController.get(getId(req));
            LOG.info("method Create/Update Product {}", product);
            req.setAttribute("product", product);
            req.setAttribute("partnerName", profileRestController.get(AuthorizedUser.id()).getName());
            req.getRequestDispatcher("createProduct.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("redirect 'method post' for Partner {}", AuthorizedUser.id());
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        if (name == null) {
            int userId = Integer.valueOf(req.getParameter("Id"));
            AuthorizedUser.setId(userId);
            User userAuthorized = profileRestController.get(AuthorizedUser.id());
            LOG.info("get User {}", userAuthorized);
            if (UsersUtil.isPartner(userAuthorized)) {
                resp.sendRedirect("partnerpage");
            } else if(UsersUtil.isRepresentative(userAuthorized)){
                resp.sendRedirect("representativepage");
            }else
                resp.sendRedirect("products");
        } else {
            final Product product = new Product(req.getParameter("name"), Integer.parseInt(req.getParameter("price")),
                    req.getParameter("description"));
                LOG.info("Create {} Product", product);
                productRestController.create(product);
            resp.sendRedirect("partnerpage");
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }
}