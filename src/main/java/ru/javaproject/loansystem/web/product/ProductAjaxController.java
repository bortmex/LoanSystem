package ru.javaproject.loansystem.web.product;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javaproject.loansystem.AuthorizedUser;
import ru.javaproject.loansystem.model.Product;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/ajax")
public class ProductAjaxController  extends AbstractProductController {

    @GetMapping(value = "/partner/product/all/{idpartner}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getAllProductForPartnerUser(@PathVariable("idpartner") int id) {
        return (List<Product>) super.getAll(id);
    }

    @GetMapping(value = "/partner/product/{id}/{partnerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Product getProductForUserPart(@PathVariable("id") int id, @PathVariable("partnerId") int partnerId) {
        return super.get(id, partnerId);
    }


    @PostMapping("/partner/product")
    public void createOrUpdate(@RequestParam("id") Integer id,
                               @RequestParam("name") String name,
                               @RequestParam("price") Integer price,
                               @RequestParam("description") String description) throws IOException {
        Product product = new Product(name, price, description);
        if (product.isNew()) {
            super.create(product, AuthorizedUser.id());
        }
    }

    @GetMapping(value = "/user/product/{id}/{partnerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Product getProductForPart(@PathVariable("id") int id, @PathVariable("partnerId") int partnerId) {
        return super.get(id, partnerId);
    }
}