package ru.javaproject.loansystem.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.javaproject.loansystem.AuthorizedUser;
import ru.javaproject.loansystem.model.User;
import ru.javaproject.loansystem.service.UserService;
import ru.javaproject.loansystem.util.PartnerUtil;
import ru.javaproject.loansystem.util.UsersUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * User: gkislin
 * Date: 22.08.2014
 */
@Controller
public class RootController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/")
    public String root() {
        return "index";
    }

    @RequestMapping(value = "/authorization", method = RequestMethod.POST)
    public String takeUser(HttpServletRequest req) {
        int userId = Integer.valueOf(req.getParameter("userId"));
        AuthorizedUser.setId(userId);
        User userAuthorized = userService.get(AuthorizedUser.id());
        if (UsersUtil.isAdmin(userAuthorized)) {
            return "redirect:/users";
        }
        if (UsersUtil.isPartner(userAuthorized)) {
            return "redirect:/showCreditAppListAndProductListForPartner";
        } else if(UsersUtil.isRepresentative(userAuthorized)){
            return "redirect:/showcreditapplistforrepresentative";
        }else
            return "redirect:/partnerlist";
    }

    @GetMapping(value = "/users")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAll());
        return "users";
    }

    @GetMapping( value = "/showAllPartnerForRepresentative")
    public String getPartnersAndCAForRepresent(Model model) {
        model.addAttribute("partnersForRep", UsersUtil.getAllPartnerList(userService.getAll()));
        return "showAndCreatePartnerForRepresentative";
    }

    @GetMapping( value = "/showCreditAppListAndProductListForPartner")
    public String getStartPageForPartner() {
        return "showCreditAppListAndProductListForPartner";
    }

    @GetMapping(value = "/partnerlist")
    public String partnersAndCAForuser(Model model) {
        model.addAttribute("partners", PartnerUtil.getAllPartner(userService.getAll()));
        return "partnerlist";
    }

}
