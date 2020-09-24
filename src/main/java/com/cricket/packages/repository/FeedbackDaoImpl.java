package com.cricket.packages.repository;

import com.cricket.packages.exception.MongoDBException;
import com.cricket.packages.persistence.Feedback;
import com.cricket.packages.request.FeedbackRequest;
import com.mongodb.MongoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

@Repository
public class FeedbackDaoImpl implements FeedbackDao {

    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public void postFeedbackToDb(Feedback feedback) throws Exception {
        try {
            mongoOperations.save(feedback);
        }catch (Exception e) {
            throw new MongoDBException("Failed to insert feedback");
        }
    }
}
