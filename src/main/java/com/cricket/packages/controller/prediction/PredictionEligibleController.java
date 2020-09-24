package com.cricket.packages.controller.prediction;

import com.cricket.packages.response.PredictEligibleResponse;
import com.cricket.packages.service.PredictionEligibleService;
import com.cricket.packages.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/prediction-eligible")
public class PredictionEligibleController {


    @Autowired
    PredictionEligibleService predictionEligibleService;

    @GetMapping
    public ResponseEntity<PredictEligibleResponse> matchesAvailableForPrediction(@RequestParam String emailID) throws Exception{


        PredictEligibleResponse predictEligibleResponse=predictionEligibleService.matchesNeedTobePredicted(emailID);

        return new ResponseEntity<>(predictEligibleResponse, HttpStatus.OK);
    }
}
