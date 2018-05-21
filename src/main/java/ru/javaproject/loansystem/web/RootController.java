package ru.javaproject.loansystem.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("/authorization")
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

/*    @GetMapping("/partner/create")
    public String createPartner(Model model) {
        model.addAttribute("user",new User("","","", Role.ROLE_PARTNER));
        return "createPartner";
    }

    @PostMapping("/partner/add")
    public String addPartner(HttpServletRequest req) {
        userService.save(new User(req.getParameter("name"),req.getParameter("email"),req.getParameter("password"), Role.ROLE_PARTNER));
        return "redirect:/showAllPartnerForRepresentative";
    }*/

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAll());
        return "allusers";
    }

    @GetMapping("/showAllPartnerForRepresentative")
    public String getPartnersAndCAForRepresent(Model model) {
        model.addAttribute("partnersForRep", UsersUtil.getAllPartnerList(userService.getAll()));
        return "showAndCreatePartnerForRepresentative";
    }

    @GetMapping("/showCreditAppListAndProductListForPartner")
    public String getStartPageForPartner() {
        return "showCreditAppListAndProductListForPartner";
    }

    @GetMapping("/partnerlist")
    public String partnersAndCAForuser(Model model) {
        model.addAttribute("partners", PartnerUtil.getAllPartner(userService.getAll()));
        return "partnerlist";
    }

}
