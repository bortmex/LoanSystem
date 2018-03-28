package ru.javaproject.loansystem.service;

import ru.javaproject.loansystem.model.CreditApplicationListProduct;
import ru.javaproject.loansystem.util.exception.NotFoundException;

import java.util.Collection;

public interface CreditApplicationListProductService {

    CreditApplicationListProduct get(int creditApplicationId) throws NotFoundException;

    Collection<CreditApplicationListProduct> getAllForCreditApplicationId(int creditApplicationId);

    Collection<CreditApplicationListProduct> getAll();

    void delete(int id) throws NotFoundException;

    CreditApplicationListProduct save(CreditApplicationListProduct creditApplicationListProduct);
}
