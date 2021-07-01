package com.pfa.backend.controllers.users;



import javax.validation.Valid;

import com.pfa.backend.models.Covoiturage;
import com.pfa.backend.models.Trajet;
import com.pfa.backend.models.User;
import com.pfa.backend.repositories.UserRepo;
import com.pfa.backend.services.UserService;
import com.pfa.backend.services.ValidationService;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller

@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ValidationService validation;


    @GetMapping(value = {"", "/home"})
    public String registerUser() {
        return "index";
    }

    @GetMapping(value = {"/registerform"})
    public String registerForm(Model model) {

        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping(value = {"/register", "/addUser"})
    public String registerUser(@RequestParam String confPass, @Valid User user, BindingResult result, RedirectAttributes attr, Model model) {
        boolean valid = false;

        if (!user.getPass().equals(confPass) || confPass.isEmpty()) {
            valid = true;
            model.addAttribute("errorconf", "Confirm Password  n'est pas valid");
        }

        if (result.hasErrors() || valid) {
            user.setPass("");
            model.addAttribute("user", user);
            return "register";
        } else {
            attr.addFlashAttribute("msg", "User Ajouter Avec Sucesss !");
            userService.saveOrUpdate(user);

        }

        return "redirect:/registerform";
    }


    @GetMapping(value = "/login")
    public String login(Model model) {
        model.addAttribute("UserService", userService.findAllUsers());
        return "loginpage";
    }
    @Autowired
    private UserRepo userRepo;
    @GetMapping(value = "/profilepage")
    public String profile(@CurrentSecurityContext(expression="authentication.name")
                                      String username, Model model){
        Long id =Long.parseLong(username);

        model.addAttribute("users", userService.getbyId(id));

        return "profile";
    }

    @GetMapping(value = "/profileedit")
    public String editprofile(@CurrentSecurityContext(expression="authentication.name")
                                      String username,Model model){
        Long id =Long.parseLong(username);
        User u = new User();
        u = userService.getbyId(id).get();
        model.addAttribute("users", u);
        System.out.println("--------------------------------------------"+userService.getbyId(id).toString());
        return "editprofilr";
    }





    @PostMapping("/editprofile")
    public String saveCovoiturage(@Valid Covoiturage covoiturage,
                                  @Param(value = "id") Long id,
                                  @Param(value = "nom") String nom,
                                  @Param(value = "prenom") String prenom
                                 , @Param(value = "tel") String tel
                                 , @Param(value = "email") String email
    ){

           userRepo.updatee(id,nom,prenom,email,tel);
        return "redirect:/home";
        }


    }







