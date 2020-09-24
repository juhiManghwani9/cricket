package com.cricket.packages.service;

import com.cricket.packages.constants.AppConstants;
import com.cricket.packages.exception.MongoDBException;
import com.cricket.packages.model.MatchResults;
import com.cricket.packages.persistence.MatchScheduleData;
import com.cricket.packages.persistence.TournamentDetailData;
import com.cricket.packages.repository.TournamentDetailsDao;
import com.cricket.packages.repository.matchSchedule.MatchScheduleDao;
import com.cricket.packages.request.MatchScheduleRequest;
import com.cricket.packages.response.GenericResponse;
import com.cricket.packages.response.MatchScheduleResponse;
import com.cricket.packages.util.AccuracyData;
import com.mongodb.MongoException;
import com.mongodb.client.result.UpdateResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Max;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class MatchScheduleService {

    @Autowired
    private MatchScheduleDao matchScheduleResponseDao;

    @Autowired
    private TournamentDetailsDao tournamentDetailsDao;

    private static final Logger LOGGER = LoggerFactory.getLogger( MatchScheduleService.class );

    public MatchScheduleResponse getAllMatchSchedule(Integer tournamentId) throws Exception {
        MatchScheduleResponse matchScheduleResponse = new MatchScheduleResponse();
        try {
            if(tournamentId ==null){
                TournamentDetailData tournamentDetailData = tournamentDetailsDao.getLatestTournament();
                if(tournamentDetailData !=null){
                    tournamentId =tournamentDetailData.getTournament_id();
                    matchScheduleResponse.setDescription(tournamentDetailData.getDescription());
                }
            }
            matchScheduleResponse.setMatchScheduleData(matchScheduleResponseDao.getMatchSchedule(tournamentId));
            matchScheduleResponse.setMessage("Successful in getting the data");
            matchScheduleResponse.setStatus(AppConstants.SUCCESS);
            return  matchScheduleResponse;
        }
        catch(Exception e) {
            LOGGER.info("failed to fetch match schedule data "+e.getLocalizedMessage());
            throw new Exception("failed to fetch match schedule data");
        }

    }

    public GenericResponse insertMatches(MatchScheduleRequest match) throws Exception {
        GenericResponse genericResponse = new GenericResponse();
            if(match != null) {
                MatchScheduleData matchScheduleData = new MatchScheduleData();
                BeanUtils.copyProperties(match,matchScheduleData);
                matchScheduleResponseDao.saveMatchSchedule(matchScheduleData);
                genericResponse.setStatus(AppConstants.SUCCESS);
                genericResponse.setMessage("Successfully Inserted");
            }
            return genericResponse;

    }
}
