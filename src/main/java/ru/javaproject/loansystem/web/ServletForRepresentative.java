package ru.javaproject.loansystem.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javaproject.loansystem.AuthorizedUser;
import ru.javaproject.loansystem.model.Role;
import ru.javaproject.loansystem.model.User;
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

public class ServletForRepresentative extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(ServletForRepresentative.class);

    private ConfigurableApplicationContext springContext;
    private ProfileRestController profileRestController;
    private ProductRestController productRestController;
    private CreditApplicationRestController creditapplicationRestController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml");
        profileRestController = springContext.getBean(ProfileRestController.class);
        productRestController = springContext.getBean(ProductRestController.class);
        creditapplicationRestController = springContext.getBean(CreditApplicationRestController.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("redirect 'method get' for Representative {}", AuthorizedUser.id());
        req.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");
        if (action == null) {
            req.setAttribute("creditapplications", creditapplicationRestController.getAll());
            req.setAttribute("representativeName", profileRestController.get(AuthorizedUser.id()).getName());
            req.setAttribute("partnersForRep", UsersUtil.getAllPartnerList(profileRestController.getAll()));
            req.getRequestDispatcher("showCreditAppListForRepresentative.jsp").forward(req, resp);
        }else if(action.equals("createpartner")){
            final User partner = new User("","","", Role.ROLE_PARTNER);
            req.setAttribute("user", partner);
            req.getRequestDispatcher("createPartner.jsp").forward(req,resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("redirect 'method post' for Representative {}", AuthorizedUser.id());
        req.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");

        if(action==null){
            final User partner = new User(req.getParameter("name"), req.getParameter("email"), req.getParameter("password"), Role.ROLE_PARTNER);
            profileRestController.create(partner);
            resp.sendRedirect("representativepage");
        }
    }
}
