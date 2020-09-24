package com.cricket.packages.response;


import com.cricket.packages.model.PredictedMatches;

import java.util.List;

public class PredictEligibleResponse {

    private String status;
    private String message;
    private List<PredictedMatches> matchData;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<PredictedMatches> getMatchData() {
        return matchData;
    }

    public void setMatchData(List<PredictedMatches> matchData) {
        this.matchData = matchData;
    }

    @Override
    public String toString() {
        return "PredictEligibleResponse{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", matchData=" + matchData +
                '}';
    }
}
