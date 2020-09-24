package com.cricket.packages.repository;

import com.cricket.packages.exception.LeaderboardException;
import com.cricket.packages.exception.MongoDBException;
import com.cricket.packages.model.AggregatedLeaderBoard;
import com.cricket.packages.model.AggregatedUserPredictionPoints;
import com.cricket.packages.persistence.UserMatchDetail;
import com.cricket.packages.service.CustomProjectAggregationOperation;
import com.mongodb.MongoException;
import com.mongodb.client.result.UpdateResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationOptions;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.LimitOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.SkipOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class UserMatchDetailDaoImpl implements UserMatchDetailDao {

    @Autowired
    private MongoOperations mongoOperations;

    static final Logger LOGGER = LoggerFactory.getLogger(UserMatchDetailDaoImpl.class);

    @Override
    public List<AggregatedLeaderBoard> globalLeaderboardData(Criteria criteria, int offset, int size) throws MongoDBException, LeaderboardException {
        try {
            MatchOperation matchOperation = Aggregation.match(criteria);
            GroupOperation groupOperation = Aggregation.group("fullName", "emailId").sum("totalPoints").as("point").count().as("count");

            SkipOperation skipOperation = Aggregation.skip(offset);
            LimitOperation limitOperation = Aggregation.limit(size);
            SortOperation sortOperation = Aggregation.sort(Sort.Direction.DESC, "point");

            Aggregation aggregation = Aggregation.newAggregation(matchOperation, groupOperation, sortOperation, skipOperation, limitOperation).withOptions(new AggregationOptions(true, false, null));
            AggregationResults<AggregatedLeaderBoard> resultSet = mongoOperations.aggregate(aggregation, "user_match_details", AggregatedLeaderBoard.class);

            return resultSet.getMappedResults();

        } catch (MongoException e) {
            throw new MongoDBException("Failed to retrieve global leaderboard data" + e.getLocalizedMessage());
        } catch (Exception e) {
            throw new LeaderboardException("Failed to retrieve global leaderboard data" + e.getLocalizedMessage());
        }
    }

    @Override
    public List<AggregatedUserPredictionPoints> getPredictedMatchesDetailsWithScheduleData(String emailId, Sort.Direction direction
            , boolean isCompleted, int offset, int size) throws Exception{

        AggregationOperation sort = null;
        MatchOperation match=null;

        try {
            match = Aggregation.match(Criteria.where("emailId").is(emailId).and("totalPoints").exists(isCompleted));
            sort = Aggregation.sort(direction, "aggregatedMatchSchedule.date");

            String query = "{ $lookup: {from: 'match_schedule' ,let: { matchID: '$matchId', tournamentID: '$tournamentId' },pipeline: [{$match: { $expr: { $and: [{$eq: ['$match_id','$$matchID']},{ $eq: [ '$tournament_id','$$tournamentID']}]}}}],as: 'aggregatedMatchSchedule'}}";
            CustomProjectAggregationOperation aggregatedMatchSchedule = new CustomProjectAggregationOperation(query);
            SkipOperation skipOperation = Aggregation.skip(offset);
            LimitOperation limitOperation = Aggregation.limit(size);
            UnwindOperation unwindOperation = Aggregation.unwind("aggregatedMatchSchedule");
            Aggregation aggregation = Aggregation.newAggregation(match, new CustomProjectAggregationOperation(query), unwindOperation, skipOperation, limitOperation, sort)
                    .withOptions(new AggregationOptions(true, false, null));

            AggregationResults<AggregatedUserPredictionPoints> resultSet = mongoOperations.aggregate(aggregation, "user_match_details", AggregatedUserPredictionPoints.class);
            return resultSet.getMappedResults();
        }
        catch (Exception ex){
            LOGGER.error("Error in predicted matches(with schedule data) fetch:"+ex);
            throw new MongoDBException("Error in getting Predicted Matches");
        }

    }

    @Override
    public List<UserMatchDetail> getUsersPredictedMatches(String emailId) throws Exception{

        try {
            Query query = new Query(Criteria.where("emailId").is(emailId));
            return mongoOperations.find(query, UserMatchDetail.class, "user_match_details");
        }
        catch(Exception ex){
            LOGGER.error("Error in predicted matches fetch:"+ex);
            throw new Exception("Error in getting Predicted Matches");
        }

    }

    @Override
    public <T> List<T> getPredictedMatchesByMatchAndTournament(int matchId , int tournamentId, Class<T> result) throws Exception{

        try {
            Query query = new Query(Criteria.where("matchId").is(matchId).and("tournamentId").is(tournamentId)).with(Sort.by(Sort.Direction.DESC,"totalPoints"));
            return mongoOperations.find(query, result, "user_match_details");
        }
        catch(Exception ex){
            LOGGER.error("Error in predicted matches fetch by match and tournment:"+ex);
            throw new Exception("Error in getting Predicted Matches");
            }
        }

    @Override
    public UpdateResult updatePoints(UserMatchDetail userMatchDetail , int totalPoints) throws Exception{

        try {
            Query query = new Query(Criteria.where("matchId").is(userMatchDetail.getMatchId()).and("tournamentId").is(userMatchDetail.getTournamentId()).and("emailId").is(userMatchDetail.getEmailId()));
            Update update = new Update();
            update.set("totalPoints", totalPoints);
            update.set("date", new Date());
            return mongoOperations.updateFirst(query, update, "user_match_details");
        }
        catch(Exception ex){
            LOGGER.error("Error in updating point:"+ex);
            throw new Exception("Error in updating point");
        }
    }

    @Override
    public void saveUserPredictedData(UserMatchDetail userMatchDetail) throws Exception{
        try{
            Query query = new Query(Criteria.where("matchId").is(userMatchDetail.getMatchId()).and("tournamentId").is(userMatchDetail.getTournamentId()).and("emailId").is(userMatchDetail.getEmailId()));
            Update update = new Update();
            update.set("fullName",userMatchDetail.getFullName());
            update.set("emailId",userMatchDetail.getEmailId());

            update.set("bestBatsmanWinnerPredictPts",userMatchDetail.getBestBatsmanWinnerPredictPts());
            update.set("predictBestBatsmanWinner",userMatchDetail.getPredictBestBatsmanWinner());

            update.set("bestBowlerWinnerPredictPts",userMatchDetail.getBestBowlerWinnerPredictPts());
            update.set("predictBestBowlerWinner",userMatchDetail.getPredictBestBowlerWinner());

            update.set("matchWinnerPredictPts",userMatchDetail.getMatchWinnerPredictPts());
            update.set("predictMatchWinner",userMatchDetail.getPredictMatchWinner());
            mongoOperations.upsert(query,update,"user_match_details");
        }
        catch(Exception e){
            LOGGER.error("Failed in saving:"+e);
            throw new Exception("Failed in Saving");
        }
    }



    }



