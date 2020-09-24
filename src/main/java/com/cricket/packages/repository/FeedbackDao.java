package com.cricket.packages.repository;

import com.cricket.packages.persistence.Feedback;

public interface FeedbackDao {

    void postFeedbackToDb(Feedback feedback) throws Exception;
}
