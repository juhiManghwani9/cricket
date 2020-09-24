package com.cricket.packages.repository;

import com.cricket.packages.exception.MongoDBException;
import com.cricket.packages.persistence.TournamentDetailData;

import java.util.List;

public interface TournamentDetailsDao {

     public List<TournamentDetailData> getTournamentHavingPointsTable() throws Exception;
     public List<TournamentDetailData> getAllTournaments() throws Exception;
     public TournamentDetailData getLatestTournament() throws Exception;
     public TournamentDetailData getTournamentById(Integer tournamentId) throws Exception;
     public void addNewTournament(TournamentDetailData tournamentDetailData) throws Exception;


}
