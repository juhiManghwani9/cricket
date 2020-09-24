package com.cricket.packages.response;

import com.cricket.packages.model.AggregatedIndividualPoints;

import java.util.List;

public class CompletedMatchesResponse {


    private String message;
    private String status;
    private List<AggregatedIndividualPoints> aggregatedIndividualPointsList;

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

    public List<AggregatedIndividualPoints> getAggregatedIndividualPointsList() {
        return aggregatedIndividualPointsList;
    }

    public void setAggregatedIndividualPointsList(List<AggregatedIndividualPoints> aggregatedIndividualPointsList) {
        this.aggregatedIndividualPointsList = aggregatedIndividualPointsList;
    }
}
