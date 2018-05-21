package ru.javaproject.loansystem.web.creditapplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.javaproject.loansystem.AuthorizedUser;
import ru.javaproject.loansystem.model.CreditApplication;
import ru.javaproject.loansystem.model.CreditApplicationListProduct;
import ru.javaproject.loansystem.model.Product;
import ru.javaproject.loansystem.service.ProductService;
import ru.javaproject.loansystem.util.PartnerUtil;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Controller
public class JspCreditApplicationController extends AbstractCreditApplicationController{

    @Autowired
    private ProductService service;

    @GetMapping("/mycreditapplication")
    public String getAllCredAppForUser(Model model) {
        model.addAttribute("creditapplication", getAllForUsersId(AuthorizedUser.id()));
        return "mycreditapp";
    }

    @GetMapping("/showCreditAppListForPartner")
    public String getAllCreditAppForPartner(Model model){
        model.addAttribute("creditapplication", PartnerUtil.getListsOfApplicationsForOnePartner((List<CreditApplication>) getAll(), AuthorizedUser.id()));
        return "showCreditAppListForPartner";
    }

    @RequestMapping("/see/create/{id}")
    public String partnersAndCA(@PathVariable int id, Model model) {
        CreditApplication creditApplication = new CreditApplication("", null, LocalDateTime.now(), "", null);
        model.addAttribute("creditApplication", creditApplication);
        List<Product> listProd = PartnerUtil.getProductListsFilteredByOnePartner((List<Product>) service.getAll(id), id);
        model.addAttribute("products", listProd);
        model.addAttribute("productsTrue", creditApplication.getProduct());
        model.addAttribute("partnerId", listProd.iterator().next().getUser().getId());
        return "createcreditapplication";
    }

    @GetMapping("/showcreditapplistforrepresentative")
    public String getPartnersAndCAForRepresent(Model model) {
        model.addAttribute("creditapplications", PartnerUtil.getListsOfApplicationsForRepr((List<CreditApplication>) getAll()));
        return "showCreditAppListForRepresentative";
    }

    @PostMapping("/creditApplication/add")
    public String addCreditApplication(HttpServletRequest req){

        final CreditApplication creditApplication = new CreditApplication(
                req.getParameter("fio"),
                LocalDate.parse(req.getParameter("dateBirth")),
                LocalDateTime.now(),
                req.getParameter("phoneNumber"),
                getReqInt(req, "anInitialFee")
        );

        Set<Product> productList = new HashSet<>();
        String parnerId = req.getParameter("partnerId");

        List<Product> partnerAllProduct = PartnerUtil.getProductListsFilteredByOnePartner((List<Product>) service.getAll(
                        Integer.parseInt(parnerId)), Integer.parseInt(parnerId));
        for (Product product : partnerAllProduct) {
            String productId = req.getParameter("product" + product.getId());
            if (productId == null) continue;
            if (productId.equals("on"))
                productList.add(product);
        }

        if (req.getParameter("idcrapp").isEmpty()) {
            create(creditApplication);
            for (Product product : productList) {
                create(new CreditApplicationListProduct(creditApplication.getId(), product.getId()));
            }
        }
        return "redirect:/partnerlist";
    }

    private int getReqInt(HttpServletRequest request, String name) {
        String paramId = Objects.requireNonNull(request.getParameter(name));
        return Integer.valueOf(paramId);
    }
}
