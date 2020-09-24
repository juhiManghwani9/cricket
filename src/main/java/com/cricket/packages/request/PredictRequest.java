package com.cricket.packages.request;

import lombok.NonNull;

import javax.validation.constraints.NotNull;

public class PredictRequest {

    private String fullName;
    @NonNull
    private String emailId;
    @NonNull
    private int matchId;
    @NonNull
    private int tournamentId;

    @NotNull(message = "Match Winner can't be empty")
    private String predictMatchWinner;
    private Integer matchWinnerPredictPts;

    @NotNull(message = "Best Batsman can't be empty")
    private String predictBestBatsmanWinner;
    private Integer bestBatsmanWinnerPredictPts;

    @NotNull(message = "Best Bowler can't be empty")
    private String predictBestBowlerWinner;
    private Integer bestBowlerWinnerPredictPts;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
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

    public String getPredictMatchWinner() {
        return predictMatchWinner;
    }

    public void setPredictMatchWinner(String predictMatchWinner) {
        this.predictMatchWinner = predictMatchWinner;
    }

    public Integer getMatchWinnerPredictPts() {
        return matchWinnerPredictPts;
    }

    public void setMatchWinnerPredictPts(Integer matchWinnerPredictPts) {
        this.matchWinnerPredictPts = matchWinnerPredictPts;
    }

    public String getPredictBestBatsmanWinner() {
        return predictBestBatsmanWinner;
    }

    public void setPredictBestBatsmanWinner(String predictBestBatsmanWinner) {
        this.predictBestBatsmanWinner = predictBestBatsmanWinner;
    }

    public Integer getBestBatsmanWinnerPredictPts() {
        return bestBatsmanWinnerPredictPts;
    }

    public void setBestBatsmanWinnerPredictPts(Integer bestBatsmanWinnerPredictPts) {
        this.bestBatsmanWinnerPredictPts = bestBatsmanWinnerPredictPts;
    }

    public String getPredictBestBowlerWinner() {
        return predictBestBowlerWinner;
    }

    public void setPredictBestBowlerWinner(String predictBestBowlerWinner) {
        this.predictBestBowlerWinner = predictBestBowlerWinner;
    }

    public Integer getBestBowlerWinnerPredictPts() {
        return bestBowlerWinnerPredictPts;
    }

    public void setBestBowlerWinnerPredictPts(Integer bestBowlerWinnerPredictPts) {
        this.bestBowlerWinnerPredictPts = bestBowlerWinnerPredictPts;
    }

    @Override
    public String toString() {
        return "PredictRequest{" +
                "fullName='" + fullName + '\'' +
                ", emailId='" + emailId + '\'' +
                ", matchId=" + matchId +
                ", tournamentId=" + tournamentId +
                ", predictMatchWinner='" + predictMatchWinner + '\'' +
                ", matchWinnerPredictPts=" + matchWinnerPredictPts +
                ", predictBestBatsmanWinner='" + predictBestBatsmanWinner + '\'' +
                ", bestBatsmanWinnerPredictPts=" + bestBatsmanWinnerPredictPts +
                ", predictBestBowlerWinner='" + predictBestBowlerWinner + '\'' +
                ", bestBowlerWinnerPredictPts=" + bestBowlerWinnerPredictPts +
                '}';
    }
}
