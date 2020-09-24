package com.cricket.packages.service;

import com.cricket.packages.constants.AppConstants;
import com.cricket.packages.persistence.TournamentDetailData;
import com.cricket.packages.repository.TournamentDetailsDao;
import com.cricket.packages.response.GenericResponse;
import com.cricket.packages.response.TournamentResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TournamentService {

    private static final Logger LOGGER = LoggerFactory.getLogger( TournamentService.class );

    @Autowired
    private TournamentDetailsDao tournamentDetailsDao;

    public TournamentResponse getPointsTableTournaments() throws Exception {
        try {
            List<TournamentDetailData> tournamentData = tournamentDetailsDao.getTournamentHavingPointsTable();
            LOGGER.info("fetched points table tournament data successfully");
            return buildResponse(AppConstants.SUCCESS,"data  fetched successfully",tournamentData);
        }catch (Exception e) {
            LOGGER.error("failed to get points table tournament data"+ e.getLocalizedMessage());
            throw new Exception("failed to fetched points table tournament data");
        }
    }
    public TournamentResponse getAllTournaments() throws Exception {
        try {
            List<TournamentDetailData> detailData = tournamentDetailsDao.getAllTournaments();
            LOGGER.info("fetched tournament data successfully");
            return buildResponse(AppConstants.SUCCESS,"data  fetched successfully", detailData);
        }catch(Exception e) {
            LOGGER.error("failed to get tournament data"+ e.getLocalizedMessage());
            throw new Exception("failed to fetched tournament data");
        }
    }

    public GenericResponse addNewTournament(TournamentDetailData data) throws Exception {
            TournamentDetailData tournamentDetailData = new TournamentDetailData();
            GenericResponse genericResponse = new GenericResponse();
            try{
                BeanUtils.copyProperties(data,tournamentDetailData);
                tournamentDetailsDao.addNewTournament(tournamentDetailData);
                LOGGER.info("Added tournament data successfully");
                genericResponse.setStatus(AppConstants.SUCCESS);
                genericResponse.setMessage("Data saved successfully");
            }catch(Exception e) {
                throw new Exception("failed to add new tournament data");
            }
            return genericResponse;
    }

    public TournamentDetailData getTournamentData(Integer tournamentId) throws Exception {
        try {
            TournamentDetailData data = tournamentDetailsDao.getTournamentById(tournamentId);
            LOGGER.info("fetched tournament data successfully for Id - {}",tournamentId);
            return data;

        } catch(Exception e) {
            throw new Exception("failed to fetch tournament data");
        }
    }

    private TournamentResponse buildResponse(String status,String message,List<TournamentDetailData> data) {
        TournamentResponse tournamentResponse = new TournamentResponse();
        if("SUCCESS".equalsIgnoreCase(status)){
            tournamentResponse.setStatus(status);
            tournamentResponse.setMessage(message);
            tournamentResponse.setTournamentsList(data);
        }else {
            tournamentResponse.setStatus(status);
            tournamentResponse.setMessage(message);
            tournamentResponse.setTournamentsList(new ArrayList<>());
        }
        return  tournamentResponse;
    }
}
