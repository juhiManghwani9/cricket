package com.cricket.packages.controller.leaderboard;

import com.cricket.packages.constants.AppConstants;
import com.cricket.packages.exception.LeaderboardException;
import com.cricket.packages.model.AggregatedLeaderBoard;
import com.cricket.packages.response.GenericResponse;
import com.cricket.packages.response.LeaderBoardResponse;
import com.cricket.packages.service.LeaderboardDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/leaderboard/global")
public class GlobalLeaderboardController {

    @Autowired
    private LeaderboardDetailService leaderboardDetailService;

    @GetMapping
    public ResponseEntity<LeaderBoardResponse> getLeaderBoardAccordingToType(@RequestParam String type, @RequestParam String value, @RequestParam int offset, @RequestParam int size) throws LeaderboardException {

        LeaderBoardResponse leaderBoardResponse = leaderboardDetailService.globalLeaderboardData(type,value,offset,size);
        if(AppConstants.SUCCESS.equalsIgnoreCase(leaderBoardResponse.getStatus())) {
            return new ResponseEntity<>(leaderBoardResponse, HttpStatus.OK);
        }
        else return new ResponseEntity<>(leaderBoardResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}