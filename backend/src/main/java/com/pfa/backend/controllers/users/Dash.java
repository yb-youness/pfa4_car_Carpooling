package com.pfa.backend.controllers.users;

import com.pfa.backend.models.User;
import com.pfa.backend.repositories.TrajetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

@RequestMapping("/admin")

public class Dash {
    @Autowired
    private TrajetRepository trajetRepository;
    @GetMapping(value ={"","/"})
    public  String admindash(Model model){
     //  model.addAttribute("listTrajets", trajetRepository.findAll());

        return "dash";
    }
}
