package ru.javaproject.util;

import ru.javaproject.model.CreditApplication;
import ru.javaproject.model.Product;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PartnerListUtil {

    //списки продуктов отфильтрованные по партнерам
    public static Map<Integer, List<Product>> getProductListsFilteredByPartner(List<Product> listProduct){
        return listProduct.stream().collect(
                Collectors.groupingBy(Product::getPartnerId)
        );
    }

    //списки продуктов отфильтрованные по одному партнеру
    public static List<Product> getProductListsFilteredByOnePartner(List<Product> listProduct, int idPartner){
        return listProduct.stream().filter(x->x.getPartnerId()==idPartner).collect(Collectors.toList());
    }

    //списки заявок для одного партнера
    public static List<CreditApplication> getListsOfApplicationsForOnePartner(List<CreditApplication> listCreditApplication, int idPartner){
        return listCreditApplication.stream().filter(x->x.getProduct().iterator().next().getPartnerId()==idPartner).collect(Collectors.toList());
    }

}
