package com.cricket.packages.controller;

import com.cricket.packages.constants.AppConstants;
import com.cricket.packages.persistence.TournamentDetailData;
import com.cricket.packages.response.GenericResponse;
import com.cricket.packages.response.TournamentResponse;
import com.cricket.packages.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tournaments")
public class TournamentController {

    @Autowired
    private TournamentService tournamentService;

    @GetMapping
    public ResponseEntity<TournamentResponse> getAllTournaments() throws Exception {
        TournamentResponse tournamentResponse = tournamentService.getAllTournaments();
        if(AppConstants.SUCCESS.equalsIgnoreCase(tournamentResponse.getStatus())){
            return new ResponseEntity<>(tournamentResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(tournamentResponse,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(path = "/points-table")
    public ResponseEntity<TournamentResponse> getPointsTableTournaments() throws Exception {
        TournamentResponse tournamentResponse = tournamentService.getPointsTableTournaments();
        if(AppConstants.SUCCESS.equalsIgnoreCase(tournamentResponse.getStatus())){
            return new ResponseEntity<>(tournamentResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(tournamentResponse,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<GenericResponse> addNewTournament(@RequestBody TournamentDetailData data) throws Exception {
        GenericResponse genericResponse = tournamentService.addNewTournament(data);
        if(AppConstants.SUCCESS.equalsIgnoreCase(genericResponse.getStatus())){
            return new ResponseEntity<>(genericResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(genericResponse,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
