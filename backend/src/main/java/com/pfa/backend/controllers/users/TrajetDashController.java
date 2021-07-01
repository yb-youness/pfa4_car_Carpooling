package com.pfa.backend.controllers.users;

import com.pfa.backend.models.Trajet;
import com.pfa.backend.repositories.CovoiturageRepo;
import com.pfa.backend.repositories.TrajetRepository;
import com.pfa.backend.repositories.UserRepo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


@Controller
public class TrajetDashController {

    @Autowired
    private TrajetRepository trajetRepository;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CovoiturageRepo covoiturageRepo;
    @GetMapping("/dash")
    public String viewDashPage(HttpServletRequest request, Model model){
        int page = 0; //default page number is 0 (yes it is weird)
        int size = 5; //default page size is 10
        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }

        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }

        model.addAttribute("listTrajets", trajetRepository.findAll(PageRequest.of(page, size)));
        //count
        model.addAttribute("count", trajetRepository.count());
        model.addAttribute("count1", userRepo.count());
        model.addAttribute("count2", covoiturageRepo.count());
        return "dash";
    }

    // @GetMapping(value ="/trajetdash")
    // public  String trajetDash(Model model){

    //     return "trajetdash";
    // }


//REFUSE TRAJET
    @GetMapping(value ="/refusertrajet/{id}" )
    public String refuserTrajer(Model model,@PathVariable("id") Long id){
       Trajet trajet=trajetRepository.findById(id).get();
       trajet.setStatus(2);
       trajetRepository.save(trajet);

        return "redirect:/trajetdashRef";
    }

    //DISPLAY ALL REFUSED TRAJTES
    @GetMapping(value ="/trajetdashRef")
    public  String TrajetRefused(Model model){

        model.addAttribute("count", trajetRepository.count());
        model.addAttribute("count1", userRepo.count());
        model.addAttribute("count2", covoiturageRepo.count());

        List<Trajet>trajet=trajetRepository.findAll();
        List<Trajet>trajetRefused  = new ArrayList<Trajet>();

        for (Trajet t:trajet) {
            if(t.getStatus()==2){
                trajetRefused.add(t);
            }
        }
        model.addAttribute("listTrajets",trajetRefused );


        return "trajetRefuse";
    }











   //ACCEPT TRAJET
    @GetMapping(value ="/acceptertrajet/{id}" )
    public String accetpttrajet(@PathVariable("id") Long id){
        Trajet trajet=trajetRepository.findById(id).get();
        trajet.setStatus(1);
        trajetRepository.save(trajet);
        return "redirect:/trajetdash";
    }


    //DISPLAY ALL ACCEPTED TRAJTES
    @GetMapping(value ="/trajetdash")
    public  String TrajetAccepté(Model model){

        model.addAttribute("count", trajetRepository.count());
        model.addAttribute("count1", userRepo.count());
        model.addAttribute("count2", covoiturageRepo.count());

        List<Trajet>trajet=trajetRepository.findAll();
        List<Trajet>trajetAccepted  = new ArrayList<Trajet>();

        for (Trajet t:trajet) {
            if(t.getStatus()==1){
                trajetAccepted.add(t);
            }
        }
          model.addAttribute("listTrajets",trajetAccepted );

        return "trajetdash";
    }

    //Search
    @GetMapping("/searchh")
    public String searchtrajet( @Param(value = "word") String word,
                               Model model){

        model.addAttribute("listTrajets", trajetRepository.searchh(word));

        return "/trajetdash";
    }



}
