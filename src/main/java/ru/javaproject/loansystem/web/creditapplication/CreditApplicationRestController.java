package ru.javaproject.loansystem.web.creditapplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javaproject.loansystem.AuthorizedUser;
import ru.javaproject.loansystem.model.CreditApplication;
import ru.javaproject.loansystem.model.CreditApplicationListProduct;
import ru.javaproject.loansystem.model.Product;
import ru.javaproject.loansystem.service.ProductService;
import ru.javaproject.loansystem.util.PartnerUtil;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = CreditApplicationRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class CreditApplicationRestController extends AbstractCreditApplicationRestController {
    public static final String REST_URL = "/rest/profile/credapp";

    @Autowired
    private ProductService service;

    @GetMapping(value = "/user/my")
    @ResponseBody
    public List<CreditApplication> getAllCredAppForUser() {
        return (List<CreditApplication>) super.getAllForUsersId(AuthorizedUser.id());
    }

    @GetMapping("/{id}")
    @ResponseBody
    public CreditApplication getForUser(@PathVariable("id") int id) {
        return super.get(id, AuthorizedUser.id());
    }

    @GetMapping("/partner/showCreditAppListForPartner")
    @ResponseBody
    public List<CreditApplication> getAllCredAppForPartner(){
        return PartnerUtil.getListsOfApplicationsForOnePartner((List<CreditApplication>) getAll(), AuthorizedUser.id());
    }

    @GetMapping("/partner/showCreditAppListForPartner/{id}")
    @ResponseBody
    public List<CreditApplication> getAllCredAppForPartnerId(@PathVariable("id") int id){
        return PartnerUtil.getListsOfApplicationsForOnePartner((List<CreditApplication>) getAll(), id);
    }

    @GetMapping("/user/create/{partnerid}")
    @ResponseBody
    public CreditApplication partnersAndCA(@PathVariable("partnerid") int partnerid) {
        CreditApplication creditApplication = new CreditApplication("", null, LocalDateTime.now(), "", null);
        List<Product> listProd = PartnerUtil.getProductListsFilteredByOnePartner(
                (List<Product>) service.getAll(partnerid), partnerid);
        creditApplication.setProduct(listProd);
        return super.create(creditApplication);
    }


    @GetMapping( value = "/resentative/showcreditapplistforrepresentative")
    @ResponseBody
    public List<CreditApplication> getPartnersAndCAForRepresent() {
        return (List<CreditApplication>) getAll();
    }

    @PostMapping(value = "/creditApplication/add/{partnerid}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreditApplication> createWithLocation(@RequestBody CreditApplication creditApplication, @PathVariable("partnerid") int partnerid) {
        CreditApplication created = super.create(creditApplication);
        /*Set<Product> productList = new HashSet<>();
        List<Product> partnerAllProduct = PartnerUtil.getProductListsFilteredByOnePartner((List<Product>)
                service.getAll(partnerid), partnerid);
        for (Product product : partnerAllProduct) {
            String productId = req.getParameter("product" + product.getId());
            if (productId == null) continue;
            if (productId.equals("on"))
                productList.add(product);
        }*/
            create(creditApplication);
            for (Product product : creditApplication.getProduct()) {
                create(new CreditApplicationListProduct(creditApplication.getId(), product.getId()));
            }
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
