package ru.javaproject.loansystem.repository;

import ru.javaproject.loansystem.model.CreditApplicationListProduct;

import java.util.Collection;

public interface CreditApplicationListProductRepository {

    CreditApplicationListProduct save(CreditApplicationListProduct creditApplicationListProduct);

    boolean deleteAll(int creditApplicationId);

    CreditApplicationListProduct get(int creditApplicationId);

    public Collection<CreditApplicationListProduct> getAllForCreditApplicationId(int creditApplicationId);

    public Collection<CreditApplicationListProduct> getAll();
}
