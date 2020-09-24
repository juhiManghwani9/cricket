package com.cricket.packages.repository.matchSchedule;

import com.cricket.packages.exception.MongoDBException;
import com.cricket.packages.model.MatchResults;
import com.cricket.packages.model.PredictedMatches;
import com.cricket.packages.persistence.MatchScheduleData;
import com.cricket.packages.util.AccuracyData;
import com.mongodb.MongoException;
import com.mongodb.client.result.UpdateResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public class MatchScheduleDaoImpl implements MatchScheduleDao {

    private static final Logger LOGGER = LoggerFactory.getLogger( MatchScheduleDaoImpl.class );

    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public List<MatchScheduleData> getMatchSchedule(Integer tournamentId) throws Exception {
        List<MatchScheduleData> matchScheduleData=null;
        try {

            Query query = new Query(Criteria.where("tournament_id").is(tournamentId)).with(Sort.by(Sort.Direction.ASC, "date"));
            matchScheduleData = mongoOperations.find(query, MatchScheduleData.class, "match_schedule");
            LOGGER.info("fetched the match schedule data for tournament Id - {} successfully",tournamentId);

            return matchScheduleData;
        }
        catch (MongoException e) {
            LOGGER.error("Failed to get Match schedule data for id - {}",tournamentId);
            throw new MongoDBException("Failed to get Match schedule data");
        } catch (Exception e) {
            LOGGER.error("Failed to get Match schedule data for id - {}",tournamentId);
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public MatchScheduleData getMatchScheduleDataForGivenMatchAndTournmanetID(Integer matchId , Integer tournamentId) throws Exception{
        try{
            Query query = new Query(Criteria.where("tournament_id").is(tournamentId).and("match_id").is(matchId));
            return mongoOperations.findOne(query,MatchScheduleData.class,"match_schedule");
        }
        catch (MongoException e) {
            LOGGER.error("Failed to get Match schedule data for given tournament and match id");
            throw new MongoDBException("Failed to get Match schedule data");
        } catch (Exception e) {
            LOGGER.error("Failed to get Match schedule data for given tournament and match id");
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<PredictedMatches>  getLastNHoursMatchesFromCurrentTime(int n) throws Exception{

        try {
            Criteria criteria = new Criteria().andOperator(Criteria.where("date").gt(new Date()), Criteria.where("date").lt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * n)));
            Query query = new Query(criteria).with(Sort.by(Sort.Direction.ASC, "date"));
            return mongoOperations.find(query, PredictedMatches.class, "match_schedule");
        }
        catch (MongoException e) {
            LOGGER.error("Failed to get Match schedule data for given window");
            throw new MongoDBException("Failed to get Match schedule data for given window");
        } catch (Exception e) {
            LOGGER.error("Failed to get Match schedule data for given window");
            throw new Exception(e.getMessage());
        }


    }

    @Override
    public UpdateResult updateMatchSchedule(MatchResults matchResults , AccuracyData accuracyData) throws Exception{

        try {
            Query query = new Query(Criteria.where("match_id").is(matchResults.getMatchId()).and("tournament_id").is(matchResults.getTournamentId()));
            Update update = new Update();
            update.set("match_winner", matchResults.getMatchWinner());
            update.set("batsman_winner", matchResults.getBestBatsman());
            update.set("bowler_winner", matchResults.getBestBowler());
            update.set("accuracy_data", accuracyData);
            return mongoOperations.updateFirst(query, update, "match_schedule");
        }
        catch(Exception e){
            LOGGER.error("Failed to update match schedule:"+e);
            throw new Exception("Failed in Updating Match Schedule");
        }
    }

    @Override
    public void saveMatchSchedule(MatchScheduleData matchScheduleData) throws Exception{
        try{
            mongoOperations.save(matchScheduleData);
        }
        catch (Exception e){
            LOGGER.error("Failed in Saving Match Schedule"+e);
        }
    }
}
