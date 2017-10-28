package ru.javaproject.web;

import org.slf4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javaproject.model.Partner;
import ru.javaproject.web.partner.PartnerRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

public class ClientServlet extends HttpServlet {
    private static final Logger LOG = getLogger(ClientServlet.class);

    private ClassPathXmlApplicationContext springContext;
    public static PartnerRestController partnerRestController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        /*partnerRestController = new PartnerRestController(new PartnerServiceImpl(new InMemoryPartnerRepositoryImpl()));*/
        springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        partnerRestController = springContext.getBean(PartnerRestController.class);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("redirect to client");
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        req.setAttribute("id",AuthorizedClient.id());
        if(action != null && !action.isEmpty()){
            if (action.equalsIgnoreCase("select")){
                int partnerId = Integer.parseInt(req.getParameter("partnerId"));
                Partner partner = partnerRestController.get(partnerId);
                req.setAttribute("name", partner.getName());
                /*req.setAttribute("list", PartnerServlet.getRepositoryProduct().getAll(partnerId));*/
                req.setAttribute("list", PartnerServlet.productRestController.getAll(partnerId));
                req.getRequestDispatcher("product.jsp").forward(req,resp);
            }
        }else {
            req.setAttribute("list", partnerRestController.getAll());
            /*req.setAttribute("listz", inMemoryQuestRepository.getAll(AuthorizedClient.id()));*/
            /*req.setAttribute("listz", PartnerServlet.getRepository().getAll());*/
            req.setAttribute("listz", PartnerServlet.questRestController.getAll(new Integer(AuthorizedClient.id())));
            //req.setAttribute("clientId", QuestsUtil.getStatisForClientAnket(questRestController.get(AuthorizedClient.id())));
            req.getRequestDispatcher("clientaddquest.jsp").forward(req,resp);
        }
    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String clientId = req.getParameter("id");
        if(clientId.equals("1")){
            AuthorizedClient.setId(1);
        } else if(clientId.equals("2"))
            AuthorizedClient.setId(2);
        resp.sendRedirect("client");
    }
}
