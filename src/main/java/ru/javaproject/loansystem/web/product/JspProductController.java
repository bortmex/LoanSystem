package ru.javaproject.loansystem.web.product;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.javaproject.loansystem.model.Product;
import ru.javaproject.loansystem.util.PartnerUtil;

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
}
