package com.pfa.backend.security;

import java.util.Collection;
import java.util.Collections;

import com.pfa.backend.models.User;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class customeuserdetail implements UserDetails {


   private User user;

   public  customeuserdetail (User user){
       super();
       this.user=user;
   }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
   
        return Collections.singleton(new SimpleGrantedAuthority(user.getRoole()));
    }

    @Override
    public String getPassword() {
     
        return user.getPass();
    }

    @Override
    public String getUsername() {
      
        return user.getId()+"";
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {

        return true;
    }
    
}
