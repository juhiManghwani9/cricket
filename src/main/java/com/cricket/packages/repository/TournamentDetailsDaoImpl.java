package com.cricket.packages.repository;

import com.cricket.packages.exception.MongoDBException;
import com.cricket.packages.persistence.TournamentDetailData;
import com.mongodb.MongoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class TournamentDetailsDaoImpl implements TournamentDetailsDao {

    @Autowired
    private MongoOperations mongoOperations;

    static final Logger LOGGER = LoggerFactory.getLogger(TournamentDetailsDaoImpl.class);


    @Override
    public List<TournamentDetailData> getTournamentHavingPointsTable() throws Exception {
        try {
            Query query = new Query(Criteria.where("points_table").is(true)).with(Sort.by(Sort.Direction.DESC, "start_date"));
            return mongoOperations.find(query, TournamentDetailData.class, "tournament_details");
        } catch (MongoException e) {
            throw new MongoDBException("Failed to fetch points table tournament documents");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<TournamentDetailData> getAllTournaments() throws Exception {
        try {
            Query query = new Query().with(Sort.by(Sort.Direction.DESC, "start_date"));
            return mongoOperations.find(query, TournamentDetailData.class, "tournament_details");
        } catch (MongoException e) {
            throw new MongoDBException("Failed to fetch all tournament documents");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public TournamentDetailData getTournamentById(Integer tournamentId) throws Exception {
        try {
            Query query = new Query(Criteria.where("tournament_id").is(tournamentId));
            return mongoOperations.find(query, TournamentDetailData.class, "tournament_details").get(0);
        } catch (MongoException e) {
            throw new MongoDBException("Failed to fetch documents");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void addNewTournament(TournamentDetailData tournamentDetailData) throws Exception {
        try {
            mongoOperations.save(tournamentDetailData);
        } catch (MongoException e) {
            throw new MongoDBException("Failed to fetch documents");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    @Override
    public TournamentDetailData getLatestTournament() throws Exception{
        try{
            LOGGER.info("Getting Latest Tournament Id when input is null");
            Query query=new Query().with(Sort.by(Sort.Direction.DESC,"_id"));
            return mongoOperations.findOne(query, TournamentDetailData.class,"tournament_details");
        }
        catch (MongoException e) {
            throw new MongoDBException("Failed to get Latest Tournament");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }
}
