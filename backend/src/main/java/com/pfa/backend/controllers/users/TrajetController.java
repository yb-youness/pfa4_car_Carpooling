package com.pfa.backend.controllers.users;

import com.pfa.backend.models.Trajet;
import com.pfa.backend.repositories.TrajetRepository;
import com.pfa.backend.services.TrajetServiceImlp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class TrajetController {

    @Autowired
    private TrajetRepository trajetRepo;


    //Display form trajet
    @GetMapping("/trajetForm")
    public String showTrajetForm(Model model) {
        //Pour afficher la date locale de system
        Trajet trajet = new Trajet();
       
        model.addAttribute("trajet", trajet);
        return "trajetform";
    }



    @Autowired
    private TrajetServiceImlp trajetservice;

    //Find All Trajet By user logged
    @GetMapping(value = "/Trajetbyid")
    public String Onetrajet(@CurrentSecurityContext(expression="authentication.name")
                                    String username, Model model,HttpServletRequest request){


        int page = 0; //default page number is 0 (yes it is weird)
        int size = 3; //default page size is 10
        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }

        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }

        Long id =Long.parseLong(username);

        //List<Trajet> trajet = trajetservice.getbyId(id);
        //System.out.println(id);
        model.addAttribute("listTrajets", trajetservice.getbyId(id,PageRequest.of(page, size)));
        return "trajet";

    }

    @GetMapping(value = "/DeleteTrajet/{id}")
    public String DeleteTrajet(@PathVariable Long id){
        trajetRepository.deleteById(id);
        return "redirect:/Trajetbyid";
    }



    //ADD TRAJET
    @PostMapping("/process_trajets")
    public String processTrajet(@Valid Trajet trajet, BindingResult result, RedirectAttributes attr) {


        if(result.hasErrors()){
            return "trajetform";
        }
        else{
            trajetRepo.save(trajet);
            attr.addFlashAttribute("msg","Trajet Ajouté Avec Réussite !");

        }

        return "redirect:/Trajetbyid";
    }




    //Display list of trajets

    @Autowired
    private TrajetRepository trajetRepository;

    @GetMapping("/Trajets")
    public String TrajetsPage(HttpServletRequest request, Model model){
        int page = 0; //default page number is 0 (yes it is weird)
        int size = 5; //default page size is 10
        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }

        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }

        model.addAttribute("listTrajets", trajetRepository.findAll(PageRequest.of(page, size)));

        /*List<Trajet> trajets = trajetServiceImlp.getAllTrajets();
        model.addAttribute("listTrajets", trajets);*/
        return "home";
    }


    //Search
    @GetMapping("/search")
    public String searchtrajet(HttpServletRequest request, @Param(value = "villedepart") String villedepart,
                               @Param(value = "Villarrive") String Villarrive,
                               @Param(value = "datedepart") Timestamp datedepart,
                               @Param(value = "date_arrivee") Timestamp date_arrivee,
                               @Param(value = "nbr_places") int nbr_places,
                               Model model){

        int page = 0; //default page number is 0 (yes it is weird)
        int size = 5; //default page size is 10
        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }

        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }
        Pageable pageable =PageRequest.of(page, size);
        model.addAttribute("listTrajets", trajetRepository.search(villedepart,Villarrive,datedepart,date_arrivee,nbr_places,pageable));

        return "/home";
    }


}
