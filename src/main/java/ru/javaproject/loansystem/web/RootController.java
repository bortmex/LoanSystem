package ru.javaproject.loansystem.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;
import ru.javaproject.loansystem.AuthorizedUser;
import ru.javaproject.loansystem.service.UserService;
import ru.javaproject.loansystem.to.UserTo;
import ru.javaproject.loansystem.util.PartnerUtil;
import ru.javaproject.loansystem.util.UsersUtil;
import ru.javaproject.loansystem.web.user.AbstractUserController;

import javax.validation.Valid;

@Controller
public class RootController extends AbstractUserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String root() {
        return "redirect:profile";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping("/createredproduct")
    public String productCreate(){
        return "createProduct";
    }

    @GetMapping("/showproductlistforpartner")
    public String getAllProductForPartner(){
        return "showProductListForPartner";
    }

    @GetMapping("/showcreditapplistforrepresentative")
    public String getPartnersAndCAForRepresent() {
        return "showCreditAppListForRepresentative";
    }

    @GetMapping("/showCreditAppListForPartner")
    public String getAllCreditAppForPartner(){
        return "showCreditAppListForPartner";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/users")
    public String getAllUsers() {
        return "allusers";
    }

    @GetMapping("/showAllPartnerForRepresentative")
    public String getPartnersAndCAForRepresent(Model model) {
        model.addAttribute("partnersForRep", UsersUtil.getAllPartnerList(userService.getAll()));
        return "showAndCreatePartnerForRepresentative";
    }

    @GetMapping("/superEditCredApp")
    public String getEditPageCredAppForSuperuser() {
        return "superEditCredApp";
    }

    @GetMapping("/superEditProduct")
    public String getEditPageProductsForSuperuser() {
        return "superEditProduct";
    }

    @GetMapping("/partnerlists")
    public String partnersAndCAForuser(Model model) {
        model.addAttribute("partners", PartnerUtil.getAllPartner(userService.getAll()));
        return "partnerlist";
    }

    @GetMapping("/profile")
    public String profile() {
        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile(@Valid UserTo userTo, BindingResult result, SessionStatus status) {
        if (!result.hasErrors()) {
            try {
                super.update(userTo, AuthorizedUser.id());
                AuthorizedUser.get().update(userTo);
                status.setComplete();
                return "redirect:profile";
            } catch (DataIntegrityViolationException ex) {
                result.rejectValue("email", "users.email.duplicate");
            }
        }
        return "profile";
    }

    @GetMapping("/register")
    public String register(ModelMap model) {
        model.addAttribute("userTo", new UserTo());
        model.addAttribute("register", true);
        return "profile";
    }


    @PostMapping("/register")
    public String saveRegister(@Valid UserTo userTo, BindingResult result, SessionStatus status, ModelMap model) {
        if (!result.hasErrors()) {
            try {
                super.create(UsersUtil.createNewFromTo(userTo));
                status.setComplete();
                return "redirect:login?message=app.registered";
            } catch (DataIntegrityViolationException ex) {
                result.rejectValue("email", "users.email.duplicate");
            }
        }
        model.addAttribute("register", true);
        return "profile";
    }

}
