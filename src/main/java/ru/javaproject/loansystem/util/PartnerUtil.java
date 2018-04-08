package ru.javaproject.loansystem.util;

import ru.javaproject.loansystem.model.CreditApplication;
import ru.javaproject.loansystem.model.Product;
import ru.javaproject.loansystem.model.Role;
import ru.javaproject.loansystem.model.User;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PartnerUtil {

    //списки продуктов отфильтрованные по партнерам
    public static Map<Integer, List<Product>> getProductListsFilteredByPartner(List<Product> listProduct){
        return listProduct.stream().collect(
                Collectors.groupingBy(x->x.getUser().getId())
        );
    }


    public static List<User> getAllPartner(List<User> userList){
        return userList.stream().filter(u -> u.getRoles().contains(Role.ROLE_PARTNER)).collect(Collectors.toList());
    }


    //списки продуктов отфильтрованные по одному партнеру
    public static List<Product> getProductListsFilteredByOnePartner(List<Product> listProduct, int idPartner){
        return listProduct.stream().filter(x->x.getUser().getId()==idPartner).collect(Collectors.toList());
    }

 /*   public static List<CreditApplicationListProduct> getCreditApplicationListProductFilteredByOneCreditApplicat(List<CreditApplicationListProduct> creditApplicationListProducts, int creditapplicationId){
        return creditApplicationListProducts.stream().filter(x->x.getCalp_id()==creditapplicationId).collect(Collectors.toList());
    }*/

    //списки заявок для одного партнера
    public static List<CreditApplication> getListsOfApplicationsForOnePartner(List<CreditApplication> listCreditApplication, int idPartner){
        return listCreditApplication.stream().filter(x->x.getProduct().size()!=0).filter(x->x.getProduct().iterator().next().getUser().getId()==idPartner).collect(Collectors.toList());
    }

}
