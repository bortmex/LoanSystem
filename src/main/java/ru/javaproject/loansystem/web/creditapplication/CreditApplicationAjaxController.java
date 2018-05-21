package ru.javaproject.loansystem.web.creditapplication;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javaproject.loansystem.AuthorizedUser;
import ru.javaproject.loansystem.model.CreditApplication;
import ru.javaproject.loansystem.model.CreditApplicationListProduct;
import ru.javaproject.loansystem.model.Product;
import ru.javaproject.loansystem.util.PartnerUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/ajax")
public class CreditApplicationAjaxController extends AbstractCreditApplicationController{

    @PostMapping("/user/credapp")
    public void createOrUpdate(@RequestParam("fio") String fio,
                                                 @RequestParam("databirth") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                                 @RequestParam("phonenumber") String phonenumber,
                                                 @RequestParam("aninitialfee") Integer aninitialfee,
                                                 @RequestParam("listproductid") String listproductid) {
        CreditApplication creditApplication = new CreditApplication(fio, date,
                LocalDateTime.now(), phonenumber, aninitialfee);
        if (creditApplication.isNew()) {
            super.create(creditApplication);
        }

        String[] arr = listproductid.replaceAll("product","").split(",");
        for (int i = 0; i < arr.length; i++) {
            super.create(new CreditApplicationListProduct(creditApplication.getId(), Integer.parseInt(arr[i])));
        }
    }

    @GetMapping(value = "/partner/credapp", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CreditApplication> getAllForPartner(){
        return PartnerUtil.getListsOfApplicationsForOnePartner((List<CreditApplication>) getAll(), AuthorizedUser.id());
    }

    @GetMapping(value = "/repres/credapp", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CreditApplication> getAllForRepres(){
        return PartnerUtil.getListsOfApplicationsForRepr((List<CreditApplication>) getAll());
    }

    @GetMapping(value = "/partner/product/all/idcred/{idcredapp}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String[] getAllProductForPartnerOnIdCredApp(@PathVariable("idcredapp") int idcredapp) {
        List<Product> listProd = super.get(idcredapp).getProduct();
        String[] arr = new String[listProd.size()];
        int i = 0;
        for (Product product:listProd) {
            arr[i++] = product.getName();
        }
        return arr;
    }

    @GetMapping(value = "/repres/product/all/idcred/{idcredapp}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String[] getAllProductForReprezentOnIdCredApp(@PathVariable("idcredapp") int idcredapp) {
        List<Product> listProd = super.get(idcredapp).getProduct();
        String[] arr = new String[listProd.size()];
        int i = 0;
        for (Product product:listProd) {
            arr[i++] = product.getName();
        }
        return arr;
    }


    @GetMapping("/partner/creditapp/{id}")
    public CreditApplication getCredAppPart(@PathVariable("id") int id) {
        return super.get(id);
    }

    @GetMapping("/repres/credapp/{id}")
    public CreditApplication getCredAppRepr(@PathVariable("id") int id) {
        return super.get(id);
    }

    @PostMapping("/partner/credapp")
    public void enabledPart(@RequestParam(value="id") int id, @RequestParam(value="bool") boolean bool) {
        super.enablePart(id, bool);
    }

    @PostMapping("/repres/credapp")
    public void enabledRepr(@RequestParam(value="id") int id, @RequestParam(value="bool") boolean bool) {
        super.enableRepr(id, bool);
    }
}
