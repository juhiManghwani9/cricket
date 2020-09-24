package com.cricket.packages.controller.authentication;

import com.cricket.packages.exception.InvalidResetPasswordException;
import com.cricket.packages.request.ResetPasswordRequest;
import com.cricket.packages.response.GenericResponse;
import com.cricket.packages.service.ResetPasswordService;
import com.cricket.packages.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/reset")
public class ResetPasswordController {

    @Autowired
    ResetPasswordService resetPasswordService;


    @PostMapping
    public ResponseEntity<GenericResponse> resetPassword(@Valid @RequestBody ResetPasswordRequest resetPasswordRequest, BindingResult bindingResult) throws Exception{

        if(bindingResult.hasErrors()){
            String message = bindingResult.getAllErrors().get(0).getDefaultMessage();
            String field = bindingResult.getAllErrors().get(0).getCodes()[0];
            throw new InvalidResetPasswordException(field + " " + message);

        }

        GenericResponse genericResponse=resetPasswordService.resetPasswordLogic(resetPasswordRequest);

        return new ResponseEntity<>(genericResponse, HttpStatus.CREATED);

    }



}
