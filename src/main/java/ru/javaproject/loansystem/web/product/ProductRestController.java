package ru.javaproject.loansystem.web.product;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javaproject.loansystem.AuthorizedUser;
import ru.javaproject.loansystem.model.Product;
import ru.javaproject.loansystem.util.PartnerUtil;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = ProductRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductRestController extends AbstractProductRestController {
    static final String REST_URL = "/rest/profile/products";

    @GetMapping("/user/see/{id}")
    @ResponseBody
    public List<Product> productAndPartnerId(@PathVariable("id") int id){
        return PartnerUtil.getProductListsFilteredByOnePartner((List<Product>)super.getAll(), id);
    }

    @GetMapping("/{id}/{idpartner}")
    @ResponseBody
    public Product getForPartner(@PathVariable("id") int id, @PathVariable("idpartner") int idpartner) {
        return super.get(id, idpartner);
    }

    @GetMapping("/partner/create/{id}")
    @ResponseBody
    public Product productCreate(@PathVariable("id") int id){
        return super.create(new Product("", 0, ""), id);
    }

    @GetMapping("/partner/showProductListForPartner")
    @ResponseBody
    public List<Product> getAllProductForPartner(){
        return (List<Product>) getAll(AuthorizedUser.id());
    }

    @PostMapping(value = "/partner/add/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> createWithLocation(@RequestBody Product product, @PathVariable("id") int id) {
        Product created = super.create(product, id);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
