package com.pfa.backend.security;

import com.pfa.backend.models.User;
import com.pfa.backend.repositories.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class coustumeUserdetailService implements UserDetailsService {

     @Autowired
     private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       User user = userRepo.findByEmail(email);
       
    if (user == null) {
        throw new UsernameNotFoundException("Not found!");
    }

    return new customeuserdetail(user);
    }

    
}
