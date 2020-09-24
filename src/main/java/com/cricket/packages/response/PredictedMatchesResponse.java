package com.cricket.packages.response;

import com.cricket.packages.model.AggregatedUserPredictionPoints;

import java.util.List;

public class PredictedMatchesResponse {

    private String message;
    private String status;
    private List<AggregatedUserPredictionPoints> aggregatedUserPredictions;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<AggregatedUserPredictionPoints> getUserPredictions() {
        return aggregatedUserPredictions;
    }

    public void setUserPredictions(List<AggregatedUserPredictionPoints> aggregatedUserPredictions) {
        this.aggregatedUserPredictions = aggregatedUserPredictions;
    }
}

