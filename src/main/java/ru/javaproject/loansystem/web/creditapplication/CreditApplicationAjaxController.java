package ru.javaproject.loansystem.web.creditapplication;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.javaproject.loansystem.AuthorizedUser;
import ru.javaproject.loansystem.model.CreditApplication;
import ru.javaproject.loansystem.model.CreditApplicationListProduct;
import ru.javaproject.loansystem.model.Product;
import ru.javaproject.loansystem.model.User;
import ru.javaproject.loansystem.to.CreditApplicationTo;
import ru.javaproject.loansystem.util.PartnerUtil;
import ru.javaproject.loansystem.util.ValidationUtil;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/ajax")
public class CreditApplicationAjaxController extends AbstractCreditApplicationController{


    @PostMapping("/user/credapp")
    public ResponseEntity<String> create(@Valid CreditApplicationTo creditApplicat, BindingResult result) {
        if (result.hasErrors()) {
            return ValidationUtil.getErrorResponse(result);
        }
        CreditApplication creditApplication = new CreditApplication(creditApplicat.getFio(), creditApplicat.getDateBirth(),
                LocalDateTime.now(), creditApplicat.getPhoneNumber(), creditApplicat.getAnInitialFee());

        if (creditApplication.isNew()) {
            super.create(creditApplication);
        }

        String[] arr = creditApplicat.getListproductid().replaceAll("product","").split(",");
        for (int i = 0; i < arr.length; i++) {
            super.create(new CreditApplicationListProduct(creditApplication.getId(), Integer.parseInt(arr[i])));
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/superuser/credapp/")
    public void update(@Valid CreditApplicationTo creditApplicat) {

        CreditApplication creditApplication = new CreditApplication(creditApplicat.getId(), creditApplicat.getFio(), creditApplicat.getDateBirth(),
                LocalDateTime.now(), creditApplicat.getPhoneNumber(), creditApplicat.getAnInitialFee());
        String listproductidparam = creditApplicat.getListproductid();
        Integer iduser = Integer.parseInt(listproductidparam.substring(listproductidparam.lastIndexOf("["), listproductidparam.length())
                                          .replaceAll("\\[useridcreatecrrap=","").replaceAll("\\]",""));
        User user = new User();
        user.setId(iduser);
        creditApplication.setUser(user);
        super.update(creditApplication);

        String[] arr = listproductidparam.replaceAll("\\[checked","").replaceAll("\\]","").split(",");

        super.deleteAllCRAPPROD(creditApplicat.getId());

        for (int i = 0; i < arr.length-1; i++) {
            String[] localArr = arr[i].split("=");
            if(localArr[1].equals("true"))
                super.create(new CreditApplicationListProduct(creditApplicat.getId(), Integer.parseInt(localArr[0])));
        }
    }

    @GetMapping(value = "/superuser/credapp", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CreditApplication> getAllCredApp(){
        return (List<CreditApplication>) getAll();
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
        return getProduct(super.get(idcredapp).getProduct());
    }

    @GetMapping(value = "/repres/product/all/idcred/{idcredapp}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String[] getAllProductForReprezentOnIdCredApp(@PathVariable("idcredapp") int idcredapp) {
        return getProduct(super.get(idcredapp).getProduct());
    }

    public static String[] getProduct(List<Product> listProd){
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


    @GetMapping("/superuser/credapp/{id}")
    public CreditApplication getCredAppSuperUser(@PathVariable("id") int id) {
        return super.get(id);
    }

    @DeleteMapping("/superuser/credapp/{id}")
    public void delete(@PathVariable("id") int id) {
        deleteCredApp(id);
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
