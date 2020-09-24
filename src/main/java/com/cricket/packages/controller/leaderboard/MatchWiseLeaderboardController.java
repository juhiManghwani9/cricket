package com.cricket.packages.controller.leaderboard;

import com.cricket.packages.constants.AppConstants;
import com.cricket.packages.response.MatchWisePointResponse;
import com.cricket.packages.service.MatchWiseLeaderboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/leaderboard/match-wise")
public class MatchWiseLeaderboardController {

    @Autowired
    private MatchWiseLeaderboardService matchWiseLeaderboardService;

    @GetMapping
    public ResponseEntity<MatchWisePointResponse>matchWiseLeaderboardController(@RequestParam int matchId, @RequestParam int tournamentId) throws Exception {

        MatchWisePointResponse matchWisePointResponse = matchWiseLeaderboardService.getMatchWiseLeaderBoard(matchId ,tournamentId);
        if(AppConstants.SUCCESS.equalsIgnoreCase(matchWisePointResponse.getStatus())) {
            return new ResponseEntity<>(matchWisePointResponse, HttpStatus.OK);
        }
        else return new ResponseEntity<>(matchWisePointResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}