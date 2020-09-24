package com.cricket.packages.controller.authentication;

import com.cricket.packages.exception.InvalidRegistrationDataException;
import com.cricket.packages.persistence.UserDetailData;
import com.cricket.packages.request.RegistrationRequest;
import com.cricket.packages.response.RegistrationResponse;
import com.cricket.packages.service.RegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("user-registration")
public class RegistrationController{

    @Autowired
    private RegistrationService registrationService;

    static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);


    @PostMapping
    ResponseEntity<RegistrationResponse> postData(@Valid @RequestBody RegistrationRequest userDetailRequest , BindingResult bindingResult) throws Exception{

        if (bindingResult.hasErrors()) {
            String message = bindingResult.getAllErrors().get(0).getDefaultMessage();
            String field = bindingResult.getAllErrors().get(0).getCodes()[0];

            LOGGER.error("^^ "+field+" "+message);
            throw new InvalidRegistrationDataException(message);
        }
        UserDetailData userDetailData=new UserDetailData();
        BeanUtils.copyProperties(userDetailRequest,userDetailData);
        RegistrationResponse registrationResponse= registrationService.saveUserData(userDetailData);

        return new ResponseEntity<>(registrationResponse, HttpStatus.CREATED);
    }

}