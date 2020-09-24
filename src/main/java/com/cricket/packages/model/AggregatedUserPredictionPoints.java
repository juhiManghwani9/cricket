package com.cricket.packages.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AggregatedUserPredictionPoints {

    private int matchId;
    private int tournamentId;
    private String predictMatchWinner;
    private Integer matchWinnerPredictPts;
    private String predictBestBatsmanWinner;
    private Integer bestBatsmanWinnerPredictPts;
    private String predictBestBowlerWinner;
    private Integer bestBowlerWinnerPredictPts;
    private Integer totalPoints;
    private AggregatedMatchSchedule aggregatedMatchSchedule;

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

    public Integer getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(Integer totalPoints) {
        this.totalPoints = totalPoints;
    }

    public AggregatedMatchSchedule getAggregatedMatchSchedule() {
        return aggregatedMatchSchedule;
    }

    public void setAggregatedMatchSchedule(AggregatedMatchSchedule aggregatedMatchSchedule) {
        this.aggregatedMatchSchedule = aggregatedMatchSchedule;
    }

    @Override
    public String toString() {
        return "AggregatedUserPredictionPoints{" +
                "matchId=" + matchId +
                ", tournamentId=" + tournamentId +
                ", predictMatchWinner='" + predictMatchWinner + '\'' +
                ", matchWinnerPredictPts=" + matchWinnerPredictPts +
                ", predictBestBatsmanWinner='" + predictBestBatsmanWinner + '\'' +
                ", bestBatsmanWinnerPredictPts=" + bestBatsmanWinnerPredictPts +
                ", predictBestBowlerWinner='" + predictBestBowlerWinner + '\'' +
                ", bestBowlerWinnerPredictPts=" + bestBowlerWinnerPredictPts +
                ", totalPoints=" + totalPoints +
                ", aggregatedMatchSchedule=" + aggregatedMatchSchedule +
                '}';
    }
}
