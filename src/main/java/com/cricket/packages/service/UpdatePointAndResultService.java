package com.cricket.packages.service;

import com.cricket.packages.constants.AppConstants;
import com.cricket.packages.exception.PointsUpdationException;
import com.cricket.packages.model.MatchResults;
import com.cricket.packages.persistence.UserMatchDetail;
import com.cricket.packages.repository.UserMatchDetailDao;
import com.cricket.packages.repository.matchSchedule.MatchScheduleDao;
import com.cricket.packages.response.GenericResponse;
import com.cricket.packages.util.AccuracyData;
import com.mongodb.client.result.UpdateResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UpdatePointAndResultService {

    @Autowired
    private UserMatchDetailDao userMatchDetailDao;

    @Autowired
    private MatchScheduleDao matchScheduleDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdatePointAndResultService.class);


    public GenericResponse updatePointsAndResult(MatchResults matchResults) throws Exception {

        AccuracyData accuracyData = new AccuracyData();
        GenericResponse genericResponse = new GenericResponse();
        List<UserMatchDetail> userMatch = userMatchDetailDao.getPredictedMatchesByMatchAndTournament(matchResults.getMatchId(), matchResults.getTournamentId(),UserMatchDetail.class);
        calculateAndPoints(matchResults, userMatch, accuracyData);

        UpdateResult updateResult = matchScheduleDao.updateMatchSchedule(matchResults, accuracyData);
        if(updateResult.getMatchedCount() == 0 && updateResult.getModifiedCount() == 0)
            throw new Exception("Invalid match/tournament ID");
        else if(updateResult.getMatchedCount() > 0 && updateResult.getModifiedCount() == 0)
            throw new Exception("Record Already updated.");
        else {
            genericResponse.setMessage("Successfully Updated");
            genericResponse.setStatus(AppConstants.SUCCESS);
        }

        return genericResponse;


    }

    public void calculateAndPoints(MatchResults matchResults, List<UserMatchDetail> userMatch, AccuracyData accuracyData) throws PointsUpdationException {
        try {
            int bestBatsmanAccuracy = 0, matchAccuracy = 0, bestBowlerAccuracy = 0;
            for (UserMatchDetail perUserPoints : userMatch) {
                int batsmanPoints = 0, mPoints = 0, bowlerPoints = 0;
                if (perUserPoints.getPredictBestBatsmanWinner() != null && matchResults.getBestBatsman() != null) {
                    if (perUserPoints.getPredictBestBatsmanWinner().equals(matchResults.getBestBatsman())) {
                        bestBatsmanAccuracy++;
                        batsmanPoints = perUserPoints.getBestBatsmanWinnerPredictPts() * 2;
                    } /*else {
                        batsmanPoints = -(perUserPoints.getBestBatsmanWinnerPredictPts() * 2);
                    }*/
                }

                if (perUserPoints.getPredictMatchWinner() != null && matchResults.getMatchWinner() != null) {
                    if (perUserPoints.getPredictMatchWinner().equals(matchResults.getMatchWinner())) {
                        matchAccuracy++;
                        mPoints =(int) (perUserPoints.getMatchWinnerPredictPts() * 1.5);
                    } /*else {
                        mPoints = -(perUserPoints.getMatchWinnerPredictPts() * 2);
                    }*/
                }

                if (perUserPoints.getPredictBestBowlerWinner() != null && matchResults.getBestBowler() != null) {
                    if (perUserPoints.getPredictBestBowlerWinner().equals(matchResults.getBestBowler())) {
                        bestBowlerAccuracy++;
                        bowlerPoints = perUserPoints.getBestBowlerWinnerPredictPts() * 3;
                    } /*else {
                        bowlerPoints = -(perUserPoints.getBestBowlerWinnerPredictPts());
                    }*/
                }
                LOGGER.info(batsmanPoints + " " + mPoints + " " + bowlerPoints);
                userMatchDetailDao.updatePoints(perUserPoints, batsmanPoints + mPoints + bowlerPoints);
            }
            int totalParticipants = userMatch.size();
            accuracyData.setBatsmanWinner((float) (bestBatsmanAccuracy * 100) / (float) totalParticipants);
            accuracyData.setMatchWinner((float) (matchAccuracy * 100) / (float) totalParticipants);
            accuracyData.setBowlerWinner((float) (bestBowlerAccuracy * 100) / (float) totalParticipants);

        } catch (Exception e) {

            LOGGER.error("Exception in point calculation:" + e);
            throw new PointsUpdationException("Failed in Updating the points");
        }

    }

}

