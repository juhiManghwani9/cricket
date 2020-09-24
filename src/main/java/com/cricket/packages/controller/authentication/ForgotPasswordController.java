package com.cricket.packages.controller.authentication;


import com.cricket.packages.response.GenericResponse;
import com.cricket.packages.service.ForgotPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/forget-password")
public class ForgotPasswordController {

    @Autowired
    private ForgotPasswordService forgotPasswordService;

    @GetMapping
    public ResponseEntity<GenericResponse> forgotPassword(@RequestParam String userId) throws Exception {

        GenericResponse forgotPasswordResponse=new GenericResponse();
        forgotPasswordService.forgetPasswordLogic(userId,forgotPasswordResponse);
        return new ResponseEntity<>(forgotPasswordResponse, HttpStatus.CREATED);
    }
}
