package ru.javaproject.web;

import org.slf4j.Logger;
import ru.javaproject.repository.PartnerRepository;
import ru.javaproject.repository.mock.InMemoryPartnerRepositoryImpl;
import ru.javaproject.model.Partner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

public class ClientServlet extends HttpServlet {
    private static final Logger LOG = getLogger(ClientServlet.class);
    private PartnerRepository partnerDao;
    private static final long serialVersionUID = 2L;
    private static String LIST_PARTNER = "clientAddQuest.jsp";
    private static String LIST_PRODUCT= "product.jsp";

    public ClientServlet() {
        super();
        partnerDao = new InMemoryPartnerRepositoryImpl();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("redirect to client");
        req.setCharacterEncoding("UTF-8");
        String forward="";
        String action = req.getParameter("action");
        if(action != null && !action.isEmpty()){
            if (action.equalsIgnoreCase("select")){
                int partnerId = Integer.parseInt(req.getParameter("partnerId"));
                Partner partner = partnerDao.getById(partnerId);
                forward = LIST_PRODUCT;
                req.setAttribute("name", partner.getName());
                req.setAttribute("list", partner.getProductDao().list());
            }
        }else {
            forward = LIST_PARTNER;
            req.setAttribute("list", partnerDao.list());
        }
        req.getRequestDispatcher(forward).forward(req,resp);
    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
