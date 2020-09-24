package com.cricket.packages.response;

import com.cricket.packages.model.AggregatedLeaderBoard;

import java.util.List;

public class LeaderBoardResponse {

   private String status;
   private String message;
   private List<AggregatedLeaderBoard> aggregatedLeaderBoardList;

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

    public List<AggregatedLeaderBoard> getAggregatedLeaderBoardList() {
        return aggregatedLeaderBoardList;
    }

    public void setAggregatedLeaderBoardList(List<AggregatedLeaderBoard> aggregatedLeaderBoardList) {
        this.aggregatedLeaderBoardList = aggregatedLeaderBoardList;
    }
}
