package com.pfa.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
     securedEnabled = true,
     jsr250Enabled = true,
     prePostEnabled = true
)
public class SecurityConf extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userdetailsService;
    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Bean
    AuthenticationProvider authenticationProvider(){
      DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
         provider.setUserDetailsService(userdetailsService);
         provider.setPasswordEncoder(new BCryptPasswordEncoder() );
         return provider;
    } 
 


    @Override
    protected void configure(HttpSecurity http) throws Exception {
           http.cors().and().csrf().disable()
            //    .exceptionHandling().authenticationEntryPoint().and()    //! this To custome A mssage For Login  
            //    .sessionManagement()
            //    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
          
               .headers().frameOptions().sameOrigin()  // Enable H2 
               .and().authorizeRequests()
               .antMatchers(
                   "/",
                   "/home",
                   "/register",
                   "/registerform",
                   "/addUser",
                   "/**/*.png",
                   "/**/*.gif",
                   "/**/*.jpg",
                   "/**/*.html",
                   "/**/*.css",
                   "/**/*.js"      
               ).permitAll()
               .and().formLogin().loginPage("/login")
               .successHandler(authenticationSuccessHandler)
               .usernameParameter("email")
               .passwordParameter("pass")
               .permitAll()
               .and().logout().permitAll()
               .and().authorizeRequests().antMatchers("/dash","/trajetdash","/userdash").access("hasRole('ROLE_ADMIN')")
               .and().authorizeRequests().antMatchers("/Trajets","/covoiturage","/trajet","/trajetForm","/Trajetbyid","/DeleteTrajet/**","/process_trajets"," /Liste_covoiturage","/add_covoiturage").access("hasRole('ROLE_USER')")
               .and().httpBasic();
           
    }
    
    
}
