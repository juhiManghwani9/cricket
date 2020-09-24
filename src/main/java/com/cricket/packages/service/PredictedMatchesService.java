package com.cricket.packages.service;

import com.cricket.packages.constants.AppConstants;
import com.cricket.packages.model.*;
import com.cricket.packages.repository.UserMatchDetailDao;
import com.cricket.packages.response.PredictedMatchesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;


import java.util.ArrayList;
import java.util.List;

@Service
public class PredictedMatchesService {


    @Autowired
    private UserMatchDetailDao userMatchDetailDao;

    public PredictedMatchesResponse getPredictedMatches(String type, String emailId, int offset, int size) throws Exception
    {

        PredictedMatchesResponse predictedMatchesResponse = new PredictedMatchesResponse();
        List<AggregatedUserPredictionPoints> listOfPredictedMatches = null;
        if(AppConstants.COMPLETED.equals(type)) {
            listOfPredictedMatches =userMatchDetailDao.getPredictedMatchesDetailsWithScheduleData(emailId ,Sort.Direction.DESC ,true,offset,size);
        }
        else if(AppConstants.ONGOING.equals(type)) {
            listOfPredictedMatches = userMatchDetailDao.getPredictedMatchesDetailsWithScheduleData(emailId ,Sort.Direction.ASC ,false,offset,size);
        }
        if(CollectionUtils.isEmpty(listOfPredictedMatches))
            listOfPredictedMatches= new ArrayList<>();

        predictedMatchesResponse.setMessage("Successfuly fetch matches");
        predictedMatchesResponse.setStatus(AppConstants.SUCCESS);
        predictedMatchesResponse.setUserPredictions(listOfPredictedMatches);
        return predictedMatchesResponse;

    }

}
