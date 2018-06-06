package ru.javaproject.loansystem.web.product;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.javaproject.loansystem.AuthorizedUser;
import ru.javaproject.loansystem.model.Product;
import ru.javaproject.loansystem.model.User;
import ru.javaproject.loansystem.to.ProductTo;
import ru.javaproject.loansystem.util.PartnerUtil;
import ru.javaproject.loansystem.util.ValidationUtil;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/ajax")
public class ProductAjaxController  extends AbstractProductController {

    @GetMapping(value = "/partner/product/all/{idpartner}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getAllProductForPartnerUser(@PathVariable("idpartner") int id) {
        return (List<Product>) super.getAll(id);
    }

    @GetMapping(value = "/superuser/product", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getAllProductForSuperUser() {
        return (List<Product>) super.getAll();
    }

    @GetMapping(value = "/partner/product/{id}/{partnerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Product getProductForUserPart(@PathVariable("id") int id, @PathVariable("partnerId") int partnerId) {
        return super.get(id, partnerId);
    }

    @GetMapping(value = "/superuser/product/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Product getProductForSuperUser(@PathVariable("id") int id) {
        return super.get(id);
    }

    @DeleteMapping("/superuser/product/{id}")
    public void deleteProd(@PathVariable("id") int id) {
        delete(id);
    }

    @GetMapping("/partner/product/getall")
    public Collection<Product> getAllProductForPartner(){
        return getAll((AuthorizedUser.safeGet().getId()));
    }

    @GetMapping("/see/{id}")
    public List<Product> productAndPartnerId(@PathVariable("id") int id){
        return PartnerUtil.getProductListsFilteredByOnePartner((List<Product>) getAll(), id);
    }

    @PostMapping("/partner/product")
    public ResponseEntity<String> createOrUpdate(@Valid ProductTo productTo, BindingResult result) throws IOException {
        if (result.hasErrors()) {
             return ValidationUtil.getErrorResponse(result);
        }
        Product product = new Product(productTo.getName(), productTo.getPrice(), productTo.getDescription());
        if (product.isNew()) {
            try {
                super.create(product, AuthorizedUser.id());
            } catch (DataIntegrityViolationException ex) {
                result.rejectValue("name", "yesduplicate");
                return ValidationUtil.getErrorResponse(result);
            }
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

   @PostMapping("/superuser/product")
    public void update(@Valid ProductTo productTo) throws IOException {
        String description = productTo.getDescription();
        Product product = new Product(productTo.getId(), productTo.getName(), productTo.getPrice(), description.substring(0, description.length()-6));
        User user = new User();
        user.setId(Integer.parseInt(description.substring(description.length()-6, description.length())));
        product.setUser(user);
        super.update(product);
    }

    @GetMapping(value = "/user/product/{id}/{partnerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Product getProductForPart(@PathVariable("id") int id, @PathVariable("partnerId") int partnerId) {
        return super.get(id, partnerId);
    }
}