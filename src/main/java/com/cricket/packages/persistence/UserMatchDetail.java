package com.cricket.packages.persistence;


import lombok.NonNull;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document(collection = "user_match_details")
public class UserMatchDetail {
    @NonNull
    private String fullName;
    @NonNull
    private String emailId;
    @NonNull
    private int matchId;
    @NonNull
    private int tournamentId;

    private String predictMatchWinner;
    private int matchWinnerPredictPts;

    private String predictBestBatsmanWinner;
    private int bestBatsmanWinnerPredictPts;

    private String predictBestBowlerWinner;
    private int bestBowlerWinnerPredictPts;

    private Integer totalPoints;
    private Date date;

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

    public int getMatchWinnerPredictPts() {
        return matchWinnerPredictPts;
    }

    public void setMatchWinnerPredictPts(int matchWinnerPredictPts) {
        this.matchWinnerPredictPts = matchWinnerPredictPts;
    }

    public String getPredictBestBatsmanWinner() {
        return predictBestBatsmanWinner;
    }

    public void setPredictBestBatsmanWinner(String predictBestBatsmanWinner) {
        this.predictBestBatsmanWinner = predictBestBatsmanWinner;
    }

    public int getBestBatsmanWinnerPredictPts() {
        return bestBatsmanWinnerPredictPts;
    }

    public void setBestBatsmanWinnerPredictPts(int bestBatsmanWinnerPredictPts) {
        this.bestBatsmanWinnerPredictPts = bestBatsmanWinnerPredictPts;
    }

    public String getPredictBestBowlerWinner() {
        return predictBestBowlerWinner;
    }

    public void setPredictBestBowlerWinner(String predictBestBowlerWinner) {
        this.predictBestBowlerWinner = predictBestBowlerWinner;
    }

    public int getBestBowlerWinnerPredictPts() {
        return bestBowlerWinnerPredictPts;
    }

    public void setBestBowlerWinnerPredictPts(int bestBowlerWinnerPredictPts) {
        this.bestBowlerWinnerPredictPts = bestBowlerWinnerPredictPts;
    }

    public Integer getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(Integer totalPoints) {
        this.totalPoints = totalPoints;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "UserMatchDetail{" +
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
                ", totalPoints=" + totalPoints +
                ", date=" + date +
                '}';
    }
}

