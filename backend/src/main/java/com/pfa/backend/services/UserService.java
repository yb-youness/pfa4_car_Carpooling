package com.pfa.backend.services;

import com.pfa.backend.models.Trajet;
import com.pfa.backend.models.User;
import com.pfa.backend.repositories.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import javax.jws.soap.SOAPBinding;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<User> findAllUsers() {
        return userRepo.findAll();
    }

    public User saveOrUpdate(User user){
      user.setPass(bCryptPasswordEncoder.encode(user.getPass()));
      userRepo.save(user);
      return user;
    }

    public Optional<User> getbyId(long id){
        Optional<User> users = userRepo.findById(id);


        return users;
    }

    
}
