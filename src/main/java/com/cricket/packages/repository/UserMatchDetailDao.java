package com.cricket.packages.repository;

import com.cricket.packages.exception.LeaderboardException;
import com.cricket.packages.exception.MongoDBException;
import com.cricket.packages.model.AggregatedLeaderBoard;
import com.cricket.packages.model.AggregatedUserPredictionPoints;
import com.cricket.packages.persistence.UserMatchDetail;
import com.mongodb.client.result.UpdateResult;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.List;

public interface UserMatchDetailDao {

    public List<AggregatedUserPredictionPoints> getPredictedMatchesDetailsWithScheduleData(String emailId, Sort.Direction direction
            , boolean isCompleted, int offset, int size) throws Exception;
    List<AggregatedLeaderBoard> globalLeaderboardData(Criteria criteria, int offset, int size) throws MongoDBException, LeaderboardException;
    public List<UserMatchDetail> getUsersPredictedMatches(String emailId) throws Exception;
    public <T> List<T> getPredictedMatchesByMatchAndTournament(int matchId , int tournamentId, Class<T> result) throws Exception;
    public void saveUserPredictedData(UserMatchDetail userMatchDetail) throws Exception;
    public UpdateResult updatePoints(UserMatchDetail userMatchDetail , int totalPoints) throws Exception;
}
