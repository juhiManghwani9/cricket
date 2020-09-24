package com.cricket.packages.controller;


import com.cricket.packages.constants.AppConstants;
import com.cricket.packages.exception.PlayersFetchException;
import com.cricket.packages.request.PlayersRequest;
import com.cricket.packages.response.PlayersResponse;
import com.cricket.packages.service.PlayersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/players-list")
public class PlayersController {

    @Autowired
    private PlayersService playersService;

    @PostMapping
    public ResponseEntity<PlayersResponse> getList(@RequestBody PlayersRequest request) throws PlayersFetchException {

        PlayersResponse playersResponse = playersService.getPlayersList(request.getTeam1(), request.getTeam2());
        if(AppConstants.SUCCESS.equalsIgnoreCase(playersResponse.getStatus())){
            return new ResponseEntity<>(playersResponse,HttpStatus.OK);
        }
        else return new ResponseEntity<>(playersResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
