package ru.javaproject.loansystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javaproject.loansystem.model.CreditApplicationListProduct;
import ru.javaproject.loansystem.repository.CreditApplicationListProductRepository;
import ru.javaproject.loansystem.util.exception.NotFoundException;

import java.util.Collection;

@Service
public class CreditApplicationListProductServiceImpl implements CreditApplicationListProductService {

    @Autowired
    private CreditApplicationListProductRepository repository;

    @Override
    public CreditApplicationListProduct get(int creditApplicationId) throws NotFoundException {
        return repository.get(creditApplicationId);
    }

    @Override
    public Collection<CreditApplicationListProduct> getAllForCreditApplicationId(int creditApplicationId) {
        return repository.getAllForCreditApplicationId(creditApplicationId);
    }

    @Override
    public Collection<CreditApplicationListProduct> getAll() {
        return repository.getAll();
    }

    @Override
    public void delete(int id) throws NotFoundException {
        repository.deleteAll(id);
    }

    @Override
    public CreditApplicationListProduct save(CreditApplicationListProduct creditApplicationListProduct) {
        return repository.save(creditApplicationListProduct);
    }
}
