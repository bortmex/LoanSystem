package ru.javaproject.loansystem.web.creaditapplicationlistproduct;

import org.springframework.web.bind.annotation.*;
import ru.javaproject.loansystem.model.CreditApplicationListProduct;

@RestController
@RequestMapping("/ajax/user/credapplistprod")
public class CreditApplicationListProductAxaxController extends AbstractCreditApplicationListProductController {

    @PostMapping("/{idcred}/{idprod}")
    public void create(@PathVariable("idcred") Integer idcred,
                       @PathVariable("idprod") Integer idprod) {
        CreditApplicationListProduct creditApplicationListProd = new CreditApplicationListProduct(idcred, idprod);
        super.create(creditApplicationListProd);
    }
}
