package com.cricket.packages.service;

import com.cricket.packages.constants.AppConstants;
import com.cricket.packages.model.MatchWiseParticipants;
import com.cricket.packages.persistence.MatchScheduleData;
import com.cricket.packages.persistence.UserMatchDetail;
import com.cricket.packages.repository.matchSchedule.MatchScheduleDao;
import com.cricket.packages.repository.UserMatchDetailDao;
import com.cricket.packages.response.MatchWisePointResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class MatchWiseLeaderboardService {

    @Autowired
    private MatchScheduleDao matchScheduleDao;

    @Autowired
    private UserMatchDetailDao userMatchDetailDao;

    private static final Logger LOGGER = LoggerFactory.getLogger( MatchWiseLeaderboardService.class );

    public MatchWisePointResponse getMatchWiseLeaderBoard(int matchId ,int tournamentId ) throws Exception{

        MatchWisePointResponse matchWisePointResponse = new MatchWisePointResponse();
        try{
            MatchScheduleData matchScheduleData = matchScheduleDao.getMatchScheduleDataForGivenMatchAndTournmanetID(matchId,tournamentId);
            if(matchScheduleData == null)
                throw new Exception("No Match Found");
            List<MatchWiseParticipants>matchWiseParticipantsList = userMatchDetailDao.getPredictedMatchesByMatchAndTournament(matchId , tournamentId,MatchWiseParticipants.class);
            if(CollectionUtils.isEmpty(matchWiseParticipantsList))
                throw new Exception("No matches found in User Match Detail");

            matchWisePointResponse.setMatchId(matchId);
            matchWisePointResponse.setTournamentId(tournamentId);
            matchWisePointResponse.setAccuracyData(matchScheduleData.getAccuracy_data());
            matchWisePointResponse.setMatchWiseParticipantsList(matchWiseParticipantsList);
            matchWisePointResponse.setBatsmanWinner(matchScheduleData.getBatsman_winner());
            matchWisePointResponse.setMatchWinner(matchScheduleData.getMatch_winner());
            matchWisePointResponse.setBowlerWinner(matchScheduleData.getBowler_winner());

            matchWisePointResponse.setStatus(AppConstants.SUCCESS);
            matchWisePointResponse.setMessage("Data Fetched successfully");

            return matchWisePointResponse;

        }
        catch (Exception e) {
            LOGGER.error(e.getLocalizedMessage());
            throw new Exception(e.getMessage());
        }
    }
}
