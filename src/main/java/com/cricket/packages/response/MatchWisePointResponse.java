package com.cricket.packages.response;

import com.cricket.packages.model.MatchWiseParticipants;
import com.cricket.packages.util.AccuracyData;

import java.util.List;

public class MatchWisePointResponse {

    private AccuracyData accuracyData;
    private int matchId;
    private int tournamentId;
    private String batsmanWinner;
    private String matchWinner;
    private String bowlerWinner;
    private List<MatchWiseParticipants> matchWiseParticipantsList;
    private String status;
    private String message;

    public String getMatchWinner() {
        return matchWinner;
    }

    public void setMatchWinner(String matchWinner) {
        this.matchWinner = matchWinner;
    }


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

    public AccuracyData getAccuracyData() {
        return accuracyData;
    }

    public void setAccuracyData(AccuracyData accuracyData) {
        this.accuracyData = accuracyData;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public int getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(int tournamentId) {
        this.tournamentId = tournamentId;
    }

    public List<MatchWiseParticipants> getMatchWiseParticipantsList() {
        return matchWiseParticipantsList;
    }

    public void setMatchWiseParticipantsList(List<MatchWiseParticipants> matchWiseParticipantsList) {
        this.matchWiseParticipantsList = matchWiseParticipantsList;
    }

    public String getBatsmanWinner() {
        return batsmanWinner;
    }

    public void setBatsmanWinner(String batsmanWinner) {
        this.batsmanWinner = batsmanWinner;
    }

    public String getBowlerWinner() {
        return bowlerWinner;
    }

    public void setBowlerWinner(String bowlerWinner) {
        this.bowlerWinner = bowlerWinner;
    }

    @Override
    public String toString() {
        return "MatchWisePointResponse{" +
                "accuracyData=" + accuracyData +
                ", matchId=" + matchId +
                ", tournamentId=" + tournamentId +
                ", batsmanWinner='" + batsmanWinner + '\'' +
                ", matchWinner='" + matchWinner + '\'' +
                ", bowlerWinner='" + bowlerWinner + '\'' +
                ", matchWiseParticipantsList=" + matchWiseParticipantsList +
                ", status='" + status + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
