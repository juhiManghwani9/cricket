package com.cricket.packages.response;

import com.cricket.packages.persistence.TournamentDetailData;

import java.util.List;

public class TournamentResponse {

    public String status;
    public String message;
    public List<TournamentDetailData> tournamentsList;

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

    public List<TournamentDetailData> getTournamentsList() {
        return tournamentsList;
    }

    public void setTournamentsList(List<TournamentDetailData> tournamentsList) {
        this.tournamentsList = tournamentsList;
    }
}
