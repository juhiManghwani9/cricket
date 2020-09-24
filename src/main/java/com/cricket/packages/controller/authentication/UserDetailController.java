package com.cricket.packages.controller.authentication;

import com.cricket.packages.response.UserDetailResponse;
import com.cricket.packages.service.MongoUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userdetail")
public class UserDetailController {

    @Autowired
    private MongoUserDetailsService userDetailsService;


    @GetMapping
    public ResponseEntity<UserDetailResponse> getUserDetail(@RequestParam String userId) throws Exception {

            UserDetailResponse userDetailResponse = userDetailsService.getUserDetail(userId);
            return new ResponseEntity<>(userDetailResponse, HttpStatus.CREATED);


    }

}
