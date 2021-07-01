package com.pfa.backend.services;

import com.pfa.backend.repositories.TrajetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.pfa.backend.models.Trajet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class TrajetServiceImlp {
    @Autowired
    private TrajetRepository trajetRepository;


    public List<Trajet> getAllTrajets() {
        return trajetRepository.findAll();
    }

    public void SaveTrajet(Trajet trajet){ trajetRepository.save(trajet); }

    public Page<Trajet> getbyId(long id,Pageable pageable){
        Page<Trajet> optional = trajetRepository.FindById_user(id,pageable);


        return optional;
    }


}
