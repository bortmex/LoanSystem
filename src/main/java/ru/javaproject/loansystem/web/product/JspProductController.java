package ru.javaproject.loansystem.web.product;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.javaproject.loansystem.AuthorizedUser;
import ru.javaproject.loansystem.model.Product;
import ru.javaproject.loansystem.util.PartnerUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class JspProductController extends AbstractProductController {

    @RequestMapping("/see/{id}")
    public String productAndPartnerId(@PathVariable("id") int id, Model model){
        List<Product> listProduct = PartnerUtil.getProductListsFilteredByOnePartner((List<Product>) getAll(), id);
        model.addAttribute("products", listProduct);
        model.addAttribute("partnerId", id);
        model.addAttribute("partnerName", listProduct.iterator().next().getUser().getName());
        return "product";
    }

    @RequestMapping("/createredproduct")
    public String productCreate(Model model){
        final Product product = new Product("", 0, "");
        model.addAttribute("product", product);
        return "createProduct";
    }


    @GetMapping("/showproductlistforpartner")
    public String getAllProductForPartner(Model model){
        model.addAttribute("products", getAll((AuthorizedUser.id())));
        return "showProductListForPartner";
    }

    @PostMapping("/partner/product/add")
    public String productAdd(HttpServletRequest req){
        create(new Product(req.getParameter("name"), Integer.parseInt(req.getParameter("price")),req.getParameter("description")), AuthorizedUser.id());
        return "redirect:/showCreditAppListAndProductListForPartner";
    }
}
