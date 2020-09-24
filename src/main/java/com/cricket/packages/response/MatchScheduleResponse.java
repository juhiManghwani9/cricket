package com.cricket.packages.response;

import com.cricket.packages.persistence.MatchScheduleData;

import java.util.List;

public class MatchScheduleResponse {

    String message;
    String status;
    String description;
    List<MatchScheduleData> matchScheduleData;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<MatchScheduleData> getMatchScheduleData() {
        return matchScheduleData;
    }

    public void setMatchScheduleData(List<MatchScheduleData> matchScheduleData) {
        this.matchScheduleData = matchScheduleData;
    }
}
