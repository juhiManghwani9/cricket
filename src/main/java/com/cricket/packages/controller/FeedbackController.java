package com.cricket.packages.controller;


import com.cricket.packages.constants.AppConstants;
import com.cricket.packages.request.FeedbackRequest;
import com.cricket.packages.response.GenericResponse;
import com.cricket.packages.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {


    @Autowired
    private FeedbackService feedbackService;

    @PostMapping
    public ResponseEntity<GenericResponse> postFeedback(@RequestBody FeedbackRequest feedbackRequest) throws Exception {

        GenericResponse genericResponse = feedbackService.postFeedbackData(feedbackRequest);
        if(AppConstants.SUCCESS.equalsIgnoreCase(genericResponse.getStatus())) {
            return new ResponseEntity<>(genericResponse, HttpStatus.OK);
        }
        else return new ResponseEntity<>(genericResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
