package com.cricket.packages.service;

import com.cricket.packages.constants.AppConstants;
import com.cricket.packages.exception.InvalidPointsException;
import com.cricket.packages.persistence.UserMatchDetail;
import com.cricket.packages.repository.UserMatchDetailDao;
import com.cricket.packages.request.PredictRequest;
import com.cricket.packages.response.GenericResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class MatchPredictionService {


    @Autowired
    private UserMatchDetailDao userMatchDetailDao;


    public GenericResponse predictedData(PredictRequest predictRequest) throws Exception
    {
        GenericResponse genericResponse = new GenericResponse();
        UserMatchDetail userMatchDetail=new UserMatchDetail();
        BeanUtils.copyProperties(predictRequest,userMatchDetail);
        try{
                if ((userMatchDetail.getMatchWinnerPredictPts() + userMatchDetail.getBestBatsmanWinnerPredictPts() + userMatchDetail.getBestBowlerWinnerPredictPts()) <= 100) {
                    userMatchDetailDao.saveUserPredictedData(userMatchDetail);
                    genericResponse.setStatus(AppConstants.SUCCESS);
                    genericResponse.setMessage("Succefully Predicted");
                }
                else{
                    throw new InvalidPointsException("Points cant be greater than 100");
                }
                return genericResponse;
        }catch(DuplicateKeyException e) {
            throw new Exception("User Already predicted for this match");
        }
    }

}
