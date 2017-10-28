package ru.javaproject.web;

import org.slf4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javaproject.model.Product;
import ru.javaproject.model.Questionnaire;
import ru.javaproject.web.product.ProductRestController;
import ru.javaproject.web.quest.QuestRestController;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

public class PartnerServlet extends HttpServlet{

    private static final Logger LOG = getLogger(PartnerServlet.class);

    private ClassPathXmlApplicationContext springContext;
    public static QuestRestController questRestController;
    public static ProductRestController productRestController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        questRestController = springContext.getBean(QuestRestController.class);
        productRestController = springContext.getBean(ProductRestController.class);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("redirect to partner");
        req.setCharacterEncoding("UTF-8");
        String compliteid = req.getParameter("compliteid");
        String action = req.getParameter("action");
        if (action != null && !action.isEmpty()) {
            if (action.equalsIgnoreCase("create")) {
                req.setAttribute("productdescription", req.getParameter("product_description"));
                req.setAttribute("productprice", Integer.parseInt(req.getParameter("product_price")));
                req.setAttribute("partnerId", Integer.parseInt(req.getParameter("productpartnerid")));
                req.setAttribute("clientId", AuthorizedClient.id());
                req.setAttribute("list", questRestController.getAll(AuthorizedPartner.id()));
                req.getRequestDispatcher("questcreate.jsp").forward(req, resp);
            }else if (action.equalsIgnoreCase("createproduct")) {
                req.setAttribute("id", Integer.parseInt(req.getParameter("partnerid")));
                req.getRequestDispatcher("productсreate.jsp").forward(req, resp);
            } else if(action.equalsIgnoreCase("forrepar")){
                int id = Integer.parseInt(req.getParameter("idforrepar"));
                Questionnaire questionnaire = questRestController.get(id);
                questionnaire.setDecisionRepresentative(1);
                questRestController.update(questionnaire,AuthorizedPartner.id());
                req.getRequestDispatcher("forrepar.jsp").forward(req, resp);
            }
        }  else if (compliteid!=null) {
            int id = Integer.parseInt(req.getParameter("idforclient"));
            Questionnaire questionnaire = questRestController.get(id);
            if (compliteid.equals("1")) {
                questionnaire.setDecisionPartner(2);
            } else if (compliteid.equals("2")){
                questionnaire.setDecisionPartner(3);
            }
            questRestController.update(questionnaire,id);
            req.getRequestDispatcher("forclient.jsp").forward(req, resp);
        }else {
            req.setAttribute("id",AuthorizedPartner.id());
            req.setAttribute("list", questRestController.getAll(AuthorizedPartner.id()));
            req.getRequestDispatcher("listquestforpartner.jsp").forward(req, resp);
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        if(req.getParameter("age")!=null){
        Questionnaire questionnaire = new Questionnaire(questRestController.getAll().size()+1,AuthorizedClient.id(),req.getParameter("floor"),Integer.parseInt(req.getParameter("age")),
                                                        Integer.parseInt(req.getParameter("income")),
                                                        req.getParameter("product_description"),
                                                        Integer.parseInt(req.getParameter("product_price")),
                                                        Integer.parseInt(req.getParameter("credit_term_day")),
                                                        Integer.parseInt(req.getParameter("productpartnerId")));
        questRestController.save(questionnaire, Integer.parseInt(req.getParameter("productpartnerId")));

        RequestDispatcher view = req.getRequestDispatcher("questcomplite.jsp");
        view.forward(req,resp);}
        else if(req.getParameter("description")!=null){
            Product product = new Product(req.getParameter("description"),
                    Integer.parseInt(req.getParameter("price")),
                    Integer.parseInt(req.getParameter("partnerId")));
            product.setId(null);
            productRestController.create(product,Integer.parseInt(req.getParameter("partnerId")));
            RequestDispatcher view = req.getRequestDispatcher("productsave.jsp");
            view.forward(req,resp);
        } else {
            String partnerId = req.getParameter("id");
            if(partnerId.equals("1")){
                AuthorizedPartner.setId(1);
            } else if(partnerId.equals("2"))
                AuthorizedPartner.setId(2);
            resp.sendRedirect("partner");
        }
    }
}
