package com.cricket.packages.controller.prediction;

import com.cricket.packages.model.*;
import com.cricket.packages.response.PredictedMatchesResponse;
import com.cricket.packages.service.PredictedMatchesService;
import com.cricket.packages.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/my-predictions")
public class PredictedMatchesController {

    @Autowired
    PredictedMatchesService predictedMatchesService;

    @GetMapping
    public ResponseEntity<PredictedMatchesResponse> getPredictedMatches(@RequestParam String type, @RequestParam @Email String emailId,
                                                                        int offset, int size) throws Exception
    {

            PredictedMatchesResponse userPrediction = predictedMatchesService.getPredictedMatches(type,emailId,offset,size);
            return new ResponseEntity<>(userPrediction, HttpStatus.OK);
        }
    }

