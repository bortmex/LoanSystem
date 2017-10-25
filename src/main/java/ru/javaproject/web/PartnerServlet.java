package ru.javaproject.web;

import org.slf4j.Logger;
import ru.javaproject.repository.QuestRepository;
import ru.javaproject.repository.mock.InMemoryQuestRepositoryImpl;
import ru.javaproject.model.Questionnaire;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

public class PartnerServlet extends HttpServlet{

    private static final Logger LOG = getLogger(PartnerServlet.class);
    private QuestRepository questDao;
    private static final long serialVersionUID = 1L;
    private static String LIST_QUEST = "listQuestForPartner.jsp";
    private static String LIST_CREATE= "quest_create.jsp";
    private static String LIST_QUEST_COMPLITE= "quest_complite.jsp";

    public PartnerServlet() {
        super();
        this.questDao = new InMemoryQuestRepositoryImpl();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("redirect to partner");
        req.setCharacterEncoding("UTF-8");
        String forward="";
        String action = req.getParameter("action");
        if (action != null && !action.isEmpty()) {
            if (action.equalsIgnoreCase("create")) {
                forward = LIST_CREATE;
                req.setAttribute("productdescription", req.getParameter("product_description"));
                req.setAttribute("productprice", Integer.parseInt(req.getParameter("product_price")));
                req.setAttribute("partnerId", Integer.parseInt(req.getParameter("productpartnerid")));
            }
        } else {
            forward = LIST_QUEST;
        }
        req.setAttribute("list", questDao.list());
        req.getRequestDispatcher(forward).forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String age = req.getRequestURI();
        String income = req.getParameter("income");
        String product_description = req.getParameter("product_description");
        String product_price = req.getParameter("product_price");
        String credit_term_day = req.getParameter("credit_term_day");
        String productpartnerid = req.getParameter("productpartnerId");
        String floor = req.getParameter("floor");

        Questionnaire questionnaire = new Questionnaire(false,Integer.parseInt(req.getParameter("age")),
                                                        Integer.parseInt(req.getParameter("income")),
                                                        req.getParameter("product_description"),
                                                        Integer.parseInt(req.getParameter("product_price")),
                                                        Integer.parseInt(req.getParameter("credit_term_day")),
                                                        Integer.parseInt(req.getParameter("productpartnerId")));
        questDao.add(questionnaire);

        RequestDispatcher view = req.getRequestDispatcher(LIST_QUEST_COMPLITE);
        view.forward(req,resp);
    }
}
