package com.cricket.packages.repository.matchSchedule;

import com.cricket.packages.model.MatchResults;
import com.cricket.packages.model.PredictedMatches;
import com.cricket.packages.persistence.MatchScheduleData;
import com.cricket.packages.util.AccuracyData;
import com.mongodb.client.result.UpdateResult;

import java.util.List;

public interface MatchScheduleDao {

    public List<MatchScheduleData> getMatchSchedule(Integer tournamentId) throws Exception;
    public MatchScheduleData getMatchScheduleDataForGivenMatchAndTournmanetID(Integer matchId , Integer tournamentId) throws Exception;
    public List<PredictedMatches> getLastNHoursMatchesFromCurrentTime(int n) throws Exception;
    public void saveMatchSchedule(MatchScheduleData matchScheduleData) throws Exception;
    public UpdateResult updateMatchSchedule(MatchResults matchResults, AccuracyData accuracyData) throws Exception;



}
