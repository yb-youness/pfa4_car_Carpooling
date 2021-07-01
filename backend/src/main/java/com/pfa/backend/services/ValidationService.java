package com.pfa.backend.services;

import java.util.HashMap;
import java.util.Map;


import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Service
public class ValidationService {

    public Map<String,String> Validation(BindingResult result){
        //! this for Error Handel
        if (result.hasErrors()){
            Map<String,String> errorUser = new HashMap<>();
            for(FieldError error :result.getFieldErrors()){
                errorUser.put(error.getField(),error.getDefaultMessage());
            }

            return errorUser;
        }
        return null;

    }
}
