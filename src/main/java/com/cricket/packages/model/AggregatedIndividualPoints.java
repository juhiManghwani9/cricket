package com.cricket.packages.model;



public class AggregatedIndividualPoints {


    private int matchId;
    private int tournamentId;
    private String predictTossWinner;
    private String predictMatchWinner;
    private String predictManOfMatch;
    private String totalPoints;
    private AggregatedMatchSchedule aggregatedMatchSchedule;

    public String getPredictManOfMatch() {
        return predictManOfMatch;
    }

    public void setPredictManOfMatch(String predictManOfMatch) {
        this.predictManOfMatch = predictManOfMatch;
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

    public String getPredictTossWinner() {
        return predictTossWinner;
    }

    public void setPredictTossWinner(String predictTossWinner) {
        this.predictTossWinner = predictTossWinner;
    }

    public String getPredictMatchWinner() {
        return predictMatchWinner;
    }

    public void setPredictMatchWinner(String predictMatchWinner) {
        this.predictMatchWinner = predictMatchWinner;
    }

    public String getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(String totalPoints) {
        this.totalPoints = totalPoints;
    }

    public AggregatedMatchSchedule getAggregatedMatchSchedule() {
        return aggregatedMatchSchedule;
    }

    public void setAggregatedMatchSchedule(AggregatedMatchSchedule aggregatedMatchSchedule) {
        this.aggregatedMatchSchedule = aggregatedMatchSchedule;
    }
}
