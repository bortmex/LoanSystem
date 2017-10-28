package ru.javaproject.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javaproject.model.Partner;
import ru.javaproject.model.Questionnaire;
import ru.javaproject.util.QuestsUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RepresentativeServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(RepresentativeServlet.class);

    private ClassPathXmlApplicationContext springContext;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml");
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("redirect to representative");
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        String compliteid = req.getParameter("compliteid");
        if (action != null && !action.isEmpty()) {
            if (action.equalsIgnoreCase("create")) {
                req.getRequestDispatcher("partnercreate.jsp").forward(req, resp);
            }
        }  else if (compliteid!=null) {
            int id = Integer.parseInt(req.getParameter("idforpartner"));
            Questionnaire questionnaire = PartnerServlet.questRestController.get(id);
                if (compliteid.equals("1")) {
                questionnaire.setDecisionRepresentative(2);
                } else if (compliteid.equals("2")){
                    questionnaire.setDecisionRepresentative(3);
                }
            PartnerServlet.questRestController.update(questionnaire,id);
                req.getRequestDispatcher("forpartner.jsp").forward(req, resp);
        }else {
            req.setAttribute("list", QuestsUtil.getQuestionnairesForRepresentative(PartnerServlet.questRestController.getAll()));
            req.getRequestDispatcher("representative.jsp").forward(req, resp);
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String compliteid = req.getParameter("compliteid");
        if (compliteid != null) {
            if (compliteid.equals("1")) {
            /*AuthorizedUser.setId(1);*/
            } else if (compliteid.equals("2"))
           /* AuthorizedUser.setId(2);*/
                resp.sendRedirect("meals");
        } else {
            Partner partner = new Partner(ClientServlet.partnerRestController.getAll().size() + 1, req.getParameter("name"), req.getParameter("email"), req.getParameter("password"));
            ClientServlet.partnerRestController.create(partner);
            RequestDispatcher view = req.getRequestDispatcher("partnercomplite.jsp");
            view.forward(req, resp);
        }
    }
}
