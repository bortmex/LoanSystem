package ru.javaproject.web.creditapplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javaproject.model.CreditApplication;
import ru.javaproject.service.CreditApplicationService;

import java.util.Collection;

@Controller
public class CreditApplicationRestController {

    @Autowired
    private CreditApplicationService service;

    public Collection<CreditApplication> getAllForUsersId(int userId){
        return service.getAllForUsersId(userId);
    }
}
