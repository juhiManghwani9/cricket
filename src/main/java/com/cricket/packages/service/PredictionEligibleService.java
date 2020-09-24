package com.cricket.packages.service;

import com.cricket.packages.constants.AppConstants;
import com.cricket.packages.model.PredictedMatches;
import com.cricket.packages.persistence.UserMatchDetail;
import com.cricket.packages.repository.UserMatchDetailDao;
import com.cricket.packages.repository.matchSchedule.MatchScheduleDao;
import com.cricket.packages.response.PredictEligibleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
public class PredictionEligibleService {


    @Autowired
    MongoOperations mongoOperations;

    @Autowired
    private MatchScheduleDao matchScheduleDao;

    @Autowired
    private UserMatchDetailDao userMatchDetailDao;



    public PredictEligibleResponse matchesNeedTobePredicted(String emailId) throws Exception{


        List<PredictedMatches> listOfMatches = matchScheduleDao.getLastNHoursMatchesFromCurrentTime(AppConstants.LASTNHOUR);
        PredictEligibleResponse predictEligibleResponse = new PredictEligibleResponse();
        if(!CollectionUtils.isEmpty(listOfMatches)) {

            List<UserMatchDetail> listOfMatchesAlreadyPredicted = userMatchDetailDao.getUsersPredictedMatches(emailId);

            if(!CollectionUtils.isEmpty(listOfMatchesAlreadyPredicted)) {

                Iterator<PredictedMatches> it = listOfMatches.iterator();
                while (it.hasNext()) {
                    PredictedMatches matchScheduleData = it.next();
                    if (listOfMatchesAlreadyPredicted.contains(matchScheduleData))
                        it.remove();
                }
            }

        }
        else{
            listOfMatches=new ArrayList<>();
        }
        predictEligibleResponse.setMessage("");
        predictEligibleResponse.setStatus("Successfull");
        predictEligibleResponse.setMatchData(listOfMatches);
        return predictEligibleResponse;
    }
}
