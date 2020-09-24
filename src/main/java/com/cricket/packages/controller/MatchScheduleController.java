package com.cricket.packages.controller;

import com.cricket.packages.constants.AppConstants;
import com.cricket.packages.exception.InvalidMatchScheduleException;
import com.cricket.packages.request.MatchScheduleRequest;
import com.cricket.packages.response.GenericResponse;
import com.cricket.packages.response.MatchScheduleResponse;
import com.cricket.packages.service.MatchScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping(path = "/match-schedule")
public class MatchScheduleController {

    @Autowired
    private MatchScheduleService matchScheduleService;


    @GetMapping
    public ResponseEntity<MatchScheduleResponse> getMatchesSchedule(@RequestParam(required = false)Integer tournamentId) throws Exception {

        MatchScheduleResponse matchScheduleResponse =matchScheduleService.getAllMatchSchedule(tournamentId);
        if(AppConstants.SUCCESS.equalsIgnoreCase(matchScheduleResponse.getStatus())){
            return  new ResponseEntity<>(matchScheduleResponse,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(matchScheduleResponse,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/insert-match")
    public ResponseEntity<GenericResponse> insertMatchesSchedule(@Valid @RequestBody MatchScheduleRequest matchInserted , BindingResult bindingResult) throws Exception {

        if (bindingResult.hasErrors()) {
            String message = bindingResult.getAllErrors().get(0).getDefaultMessage();
            String field = bindingResult.getAllErrors().get(0).getCodes()[0];

            throw new InvalidMatchScheduleException(field+" "+message);
        }
        GenericResponse genericResponse =matchScheduleService.insertMatches(matchInserted);
        return new ResponseEntity<>(genericResponse,HttpStatus.OK);

    }
}
