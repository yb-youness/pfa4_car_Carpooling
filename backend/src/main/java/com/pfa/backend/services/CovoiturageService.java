package com.pfa.backend.services;

import com.pfa.backend.models.Covoiturage;
import com.pfa.backend.models.Trajet;
import com.pfa.backend.repositories.CovoiturageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CovoiturageService {


    @Autowired
    private CovoiturageRepo covoiturageRepo;

    public List<Covoiturage> getbyId(long id){
        List<Covoiturage> covoiturages = covoiturageRepo.FindById_user(id);


        return covoiturages;
    }


}

