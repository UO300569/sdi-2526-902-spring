package com.uniovi.sdi.sdi2526902spring.controllers;

import com.uniovi.sdi.sdi2526902spring.entities.Mark;
import com.uniovi.sdi.sdi2526902spring.entities.User;
import com.uniovi.sdi.sdi2526902spring.services.RolesService;
import com.uniovi.sdi.sdi2526902spring.services.SecurityService;
import com.uniovi.sdi.sdi2526902spring.services.UsersService;
import com.uniovi.sdi.sdi2526902spring.validators.SignUpFormValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
public class UsersController {
    private final UsersService usersService;
    private final SecurityService securityService;
    private final SignUpFormValidator signUpFormValidator;
    private final RolesService rolesService;

    public UsersController(UsersService usersService, SecurityService securityService, SignUpFormValidator
            signUpFormValidator, RolesService rolesService) {
        this.usersService = usersService;
        this.securityService = securityService;
        this.signUpFormValidator = signUpFormValidator;
        this.rolesService = rolesService;
    }

    @GetMapping("/user/list")
    public String getListado(Model model, Pageable pageable, @RequestParam(value = "searchText", required = false) String searchText) {
        Page<User> users = usersService.searchUsersByNameAndLastName(pageable, searchText);

        model.addAttribute("usersList", users.getContent());
        model.addAttribute("page", users);

        return "user/list";
    }

    @GetMapping(value = "/user/add")
    public String getUser(Model model, Pageable pageable) {
        model.addAttribute("rolesList", rolesService.getRoles());
        Page<User> users = usersService.getUsers(pageable);
        model.addAttribute("usersList", users.getContent());
        return "user/add";
    }

    @PostMapping(value = "/user/add")
    public String setUser(@ModelAttribute User user) {
        usersService.addUser(user);
        return "redirect:/user/list";
    }

    @GetMapping("/user/details/{id}")
    public String getDetail(Model model, @PathVariable Long id) {
        model.addAttribute("user", usersService.getUser(id));
        return "user/details";
    }

    @GetMapping("/user/delete/{id}")
    public String delete(@PathVariable Long id) {
        usersService.deleteUser(id);
        return "redirect:/user/list";
    }


    @PostMapping("/signup")
    public String signup(@Validated User user, BindingResult result) {
        signUpFormValidator.validate(user, result);
        if (result.hasErrors()) {
            return "signup";
        }
        user.setRole(rolesService.getRoles()[0]);
        usersService.addUser(user);
        securityService.autoLogin(user.getDni(), user.getPasswordConfirm());
        return "redirect:home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/home")
    public String home(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String dni = auth.getName();
        User activeUser = usersService.getUserByDni(dni);
        model.addAttribute("marksList", activeUser.getMarks());
        return "home";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @GetMapping(value = "/user/edit/{id}")
    public String getEdit(Model model, @PathVariable Long id) {
        User user = usersService.getUser(id);
        model.addAttribute("user", user);
        return "user/edit";
    }

    @PostMapping(value = "/user/edit/{id}")
    public String setEdit(@ModelAttribute User user, @PathVariable Long id) {
        User originalUser = usersService.getUser(id);
        originalUser.setName(user.getName());
        originalUser.setLastName(user.getLastName());
        originalUser.setDni(user.getDni());
        usersService.updateOriginalUser(originalUser);
        return "redirect:/user/details/" + id;
    }

    @GetMapping("/user/list/update")
    public String updateList(Model model, Pageable pageable,
                             @RequestParam(value = "searchText", required = false) String searchText) {

        Page<User> users = usersService.searchUsersByNameAndLastName(pageable, searchText);

        model.addAttribute("usersList", users.getContent());

        return "user/list :: usersTable";
    }


}

