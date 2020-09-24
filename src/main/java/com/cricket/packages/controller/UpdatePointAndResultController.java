package com.cricket.packages.controller;


import com.cricket.packages.model.MatchResults;
import com.cricket.packages.response.GenericResponse;
import com.cricket.packages.response.RegistrationResponse;
import com.cricket.packages.service.MatchScheduleService;
import com.cricket.packages.service.UpdatePointAndResultService;
import com.cricket.packages.service.MatchPredictionService;
import com.cricket.packages.util.AccuracyData;
import com.cricket.packages.util.CommonUtil;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/update-results")
public class UpdatePointAndResultController {



    @Autowired
    private MatchScheduleService matchScheduleService;

    @Autowired
    private MatchPredictionService userMatchService;

    @Autowired
    private UpdatePointAndResultService updatePointAndResultService;

    @PutMapping
    ResponseEntity<GenericResponse> postMatchResults(@Valid @RequestBody MatchResults matchResults) throws Exception
    {

        GenericResponse genericResponse =updatePointAndResultService.updatePointsAndResult(matchResults);
        return new ResponseEntity<>(genericResponse, HttpStatus.OK);
    }
}
