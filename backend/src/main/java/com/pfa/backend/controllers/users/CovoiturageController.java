package com.pfa.backend.controllers.users;

import com.pfa.backend.models.Covoiturage;
import com.pfa.backend.models.Trajet;
import com.pfa.backend.models.User;
import com.pfa.backend.repositories.CovoiturageRepo;
import com.pfa.backend.repositories.TrajetRepository;
import com.pfa.backend.services.CovoiturageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.awt.peer.LabelPeer;
import java.util.List;

@Controller
public class CovoiturageController {

    @Autowired
    private CovoiturageRepo covoiturageRepo;

    @GetMapping("/Liste_covoiturage")
    public String ListeCovoiturage(Model model){
            Covoiturage covoiturage = new Covoiturage();
            model.addAttribute("covoiturage", covoiturage);
            return "home";
        }


    @Autowired
    private TrajetRepository trajetRepository;
    @PostMapping("/add_covoiturage")
    public String saveCovoiturage(@Valid Covoiturage covoiturage,
                                  @Param(value = "nbr_places") Long nbr_places,
                                  @Param(value = "trajet_id") Long trajet_id,
                                  @Param(value = "id_user") String id_user
                                  ){
            Trajet trajet = trajetRepository.findById(trajet_id).get();
            if (nbr_places > trajet.getNbr_places()){

                return "redirect:/Trajets";
            }
            else {
                covoiturageRepo.insertUser(nbr_places, trajet_id, Long.parseLong(id_user));

                int countt = trajet.getNbr_places() - Integer.parseInt(nbr_places + "");
                trajet.setNbr_places(countt);
                trajetRepository.save(trajet);
                //System.out.print("==========================================");
                //System.out.println(countt);

                //covoiturageRepo.save(covoiturage);


                return "redirect:/Trajets";
            }

       // covoiturageRepo.save(covoiturage);
     }

     //get covoiturage by id
    @Autowired
    private CovoiturageService covoiturageService;

    @GetMapping("/Covoiturage")
    public String Covoiturage(@CurrentSecurityContext(expression="authentication.name")
                                          String username, Model model){

        Long id =Long.parseLong(username);

        List<Covoiturage> covoiturages = covoiturageService.getbyId(id);
        //System.out.println("--------------------------------------"+id);
        model.addAttribute("listCovoiturage", covoiturages);

        return "/covoiturage";
    }


    @GetMapping("/Deletecovoiturage")
    public String DelCovoiturage(@Param(value = "nbr_places") Long nbr_places,
                                 @Param(value = "id") Long id,
                                 @Param(value = "trajet_id") Long trajet_id
                                 ){

        covoiturageRepo.deleteById(id);
        Trajet trajet = trajetRepository.findById(trajet_id).get();
        int countt = trajet.getNbr_places()+ Integer.parseInt(nbr_places+"");
        trajet.setNbr_places(countt);
        trajetRepository.save(trajet);


        return "redirect:/Trajets";
    }



}
