package com.pfa.backend.controllers.users;

import com.pfa.backend.repositories.CovoiturageRepo;
import com.pfa.backend.repositories.TrajetRepository;
import com.pfa.backend.repositories.UserRepo;
import com.pfa.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/")
public class UserDashController {

    @Autowired
    private UserService userService;
    @Autowired
    private TrajetRepository trajetRepository;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CovoiturageRepo covoiturageRepo;
    @GetMapping(value ="/userdash")
    public  String userDash(Model model){
        model.addAttribute("UserService", userService.findAllUsers());
        model.addAttribute("count", trajetRepository.count());
        model.addAttribute("count1", userRepo.count());
        model.addAttribute("count2", covoiturageRepo.count());
        return "userdash";
    }
   
}
