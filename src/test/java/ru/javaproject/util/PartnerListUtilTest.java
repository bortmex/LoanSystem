package ru.javaproject.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javaproject.model.CreditApplication;
import ru.javaproject.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PartnerListUtilTest {

    private static List<Product> listProduct;
    private static List<CreditApplication> listCreditApplicationList;

    @Before
    public void executedInitialization(){
        listProduct = new ArrayList<>();
        listCreditApplicationList = new ArrayList<>();
        Product product1 = new Product(1, "Ferrari", 140000, 1);
        Product product2 = new Product(2, "Dodge", 1111000, 1);
        Product product3 = new Product(3, "Носки", 100, 2);
        Product product4 = new Product(4, "Цветы", 20000, 2);
        Product product5 = new Product(5, "Путевка в космос", 123340000, 4);

        listProduct.add(product1);
        listProduct.add(product2);
        listProduct.add(product3);
        listProduct.add(product4);
        listProduct.add(product5);

        Map<Product, Integer> map = new HashMap<>();
        map.put(listProduct.get(0), 2);
        map.put(listProduct.get(1), 1);
/*        CreditApplication creditApplication = new CreditApplication(1, 1, "Вася Громов",
                                                                        LocalDateTime.now(), LocalDateTime.now(),
                                                                        "8910125333", map, 4344);
        Map<Product, Integer> map1 = new HashMap<>();
        map1.put(listProduct.get(2), 12);
        map1.put(listProduct.get(3), 11);
        CreditApplication creditApplication1 = new CreditApplication(2, 2, "Вася12 Громов12",
                                                                        LocalDateTime.now(), LocalDateTime.now(),
                                                                        "891021233", map1, 122344);
        Map<Product, Integer> map2 = new HashMap<>();
        map2.put(listProduct.get(4), 5);
        CreditApplication creditApplication2 = new CreditApplication(3, 3, "Вася134 Громов134",
                                                                        LocalDateTime.now(), LocalDateTime.now(),
                                                                        "891013", map2, 11232344);*/

/*        listCreditApplicationList.add(creditApplication);
        listCreditApplicationList.add(creditApplication1);
        listCreditApplicationList.add(creditApplication2);*/


    }

    @Test
    public void getProductListsFilteredByPartner() throws Exception {

        HashMap<Integer, List<Product>> mapsDetect = new HashMap<>();
        List<Product> list1 = new ArrayList<>();
        list1.add(listProduct.get(0));
        list1.add(listProduct.get(1));
        mapsDetect.put(1,list1);
        List<Product> list2 = new ArrayList<>();
        list2.add(listProduct.get(2));
        list2.add(listProduct.get(3));
        mapsDetect.put(2,list2);
        List<Product> list3 = new ArrayList<>();
        list3.add(listProduct.get(4));
        mapsDetect.put(4,list3);

        Assert.assertEquals(mapsDetect,PartnerListUtil.getProductListsFilteredByPartner(listProduct));
    }

    @Test
    public void getproductListsFilteredByOnePartner() throws Exception {
        List<Product> listDetect = new ArrayList<>();
        listDetect.add(listProduct.get(2));
        listDetect.add(listProduct.get(3));

        Assert.assertEquals(listDetect, PartnerListUtil.getProductListsFilteredByOnePartner(listProduct, 2));
    }

    @Test
    public void getListsOfApplicationsForOnePartner() throws Exception {
        List<CreditApplication> listDetect = new ArrayList<>();
        listDetect.add(listCreditApplicationList.get(2));

        Assert.assertEquals(listDetect, PartnerListUtil.getListsOfApplicationsForOnePartner(listCreditApplicationList, 4));
    }

}