package ru.javaproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javaproject.model.Partner;
import ru.javaproject.repository.PartnerRepository;
import ru.javaproject.util.exception.NotFoundException;

import java.util.List;

import static ru.javaproject.util.ValidationUtil.checkNotFoundWithId;

@Service
public class PartnerServiceImpl implements PartnerService {

    private final PartnerRepository repository;

    @Autowired
    public PartnerServiceImpl(PartnerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Partner save(Partner partner, int partnerId) {
        return repository.save(partner, partnerId);
    }

    @Override
    public void delete(int id, int partnerId) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id,partnerId),id);
    }

    @Override
    public Partner get(int id, int partnerId) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id, partnerId), id);
    }

    @Override
    public Partner get(int id) throws NotFoundException {
        return repository.get(id);
    }


    @Override
    public List<Partner> getAll(int partnerId) {
        return repository.getAll(partnerId);
    }
    @Override
    public List<Partner> getAll() {
        return repository.getAll();
    }
}
