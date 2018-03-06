package ru.javaproject.web;

import org.jboss.logging.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javaproject.AuthorizedUser;
import ru.javaproject.model.CreditApplication;
import ru.javaproject.model.Product;
import ru.javaproject.model.Role;
import ru.javaproject.model.User;
import ru.javaproject.util.PartnerListUtil;
import ru.javaproject.util.UsersUtil;
import ru.javaproject.web.creditapplication.CreditApplicationRestController;
import ru.javaproject.web.product.ProductRestController;
import ru.javaproject.web.user.ProfileRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class ProductServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(ProductServlet.class.getName());

    private ConfigurableApplicationContext springContext;
    private ProductRestController productRestController;
    private ProfileRestController profileRestController;
    private CreditApplicationRestController creditapplicationRestController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml");
        productRestController = springContext.getBean(ProductRestController.class);
        profileRestController = springContext.getBean(ProfileRestController.class);
        creditapplicationRestController = springContext.getBean(CreditApplicationRestController.class);
    }

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        int answer = 0;
        try {
            answer = checkAction(req);
        } catch (SQLException sql_e) {
            throw new IOException(sql_e.getMessage());
        }

        if (answer == 0) {
            /*req.setAttribute("products", productRestController.getAll());*/
            req.setAttribute("userId", AuthorizedUser.id());
            List<User> userList = profileRestController.getAll();
            if (!UsersUtil.isPartner(AuthorizedUser.id(), userList)) {
                List<User> dsf1 = userList.stream().filter(u -> u.getRoles().contains(Role.ROLE_PARTNER)).collect(Collectors.toList());
                req.setAttribute("partners", dsf1);
                List<CreditApplication> sdf = (List<CreditApplication>) creditapplicationRestController.getAllForUsersId(AuthorizedUser.id());
                /*Collection<Product> products =  productRestController.getAll();*/

                req.setAttribute("creditapplication", sdf);
                req.getRequestDispatcher("partnerList.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("createCreditApplication.jsp").forward(req, resp);
            }
            return;
        }

        if (answer == 1) {

            req.setAttribute("products", PartnerListUtil.getProductListsFilteredByOnePartner((List<Product>) productRestController.getAll(Integer.parseInt(req.getParameter("id"))), Integer.parseInt(req.getParameter("id"))));
            req.getRequestDispatcher("product.jsp").forward(req, resp);
            return;
        }

        if (answer == 2) {

        }

        if (answer == 3) {

        }

        //req.setAttribute("form", form);
    }

    private int checkAction(HttpServletRequest req) throws SQLException {
        if (req.getParameter("action") != null) {
            return 1;
        }
        if (req.getParameter("Edit") != null) {
            return 2;
        }
        if (req.getParameter("MoveGroup") != null) {
            return 3;
        }
        if (req.getParameter("Delete") != null) {
            /*if (req.getParameter("studentId") != null) {
                Student s = new Student();
                s.setStudentId(Integer.parseInt(req.getParameter("studentId")));
                ManagementSystem.getInstance().deleteStudent(s);
            }*/
            return 0;
        }
        return 0;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("redirect 'method get' to product");
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("redirect 'method post' to product");
        int userId = Integer.valueOf(req.getParameter("userId"));
        AuthorizedUser.setId(userId);
        processRequest(req, resp);
    }
}
