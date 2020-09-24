package com.cricket.packages.service;

import com.cricket.packages.constants.AppConstants;
import com.cricket.packages.persistence.Feedback;
import com.cricket.packages.repository.FeedbackDao;
import com.cricket.packages.request.FeedbackRequest;
import com.cricket.packages.response.GenericResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {


    @Autowired
    private FeedbackDao feedbackDao;

    private static final Logger LOGGER = LoggerFactory.getLogger( FeedbackService.class );


    public GenericResponse postFeedbackData(FeedbackRequest feedbackRequest) throws Exception {

        GenericResponse genericResponse=new GenericResponse();
        Feedback feedbackData=new Feedback();
        try{
            BeanUtils.copyProperties(feedbackRequest,feedbackData);
            feedbackDao.postFeedbackToDb(feedbackData);
            LOGGER.info("Feedback inserted successfully");
            genericResponse.setMessage("Feedback Inserted Successfully");
            genericResponse.setStatus(AppConstants.SUCCESS);
        }
        catch (Exception e) {
            LOGGER.info("Failed to insert  Feedback "+e.getLocalizedMessage());
            throw new Exception("failed add feedback data");
        }
        return genericResponse;
    }
}
