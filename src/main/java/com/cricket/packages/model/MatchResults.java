package com.cricket.packages.model;

public class MatchResults {
    private int matchId;
    private int tournamentId;
    private String matchWinner;
    private String bestBatsman;
    private String bestBowler;

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

    public String getMatchWinner() {
        return matchWinner;
    }

    public void setMatchWinner(String matchWinner) {
        this.matchWinner = matchWinner;
    }

    public String getBestBatsman() {
        return bestBatsman;
    }

    public void setBestBatsman(String bestBatsman) {
        this.bestBatsman = bestBatsman;
    }

    public String getBestBowler() {
        return bestBowler;
    }

    public void setBestBowler(String bestBowler) {
        this.bestBowler = bestBowler;
    }

    @Override
    public String toString() {
        return "MatchResults{" +
                "match_id=" + matchId +
                ", tournamentId=" + tournamentId +
                ", matchWinner='" + matchWinner + '\'' +
                ", bestBatsman='" + bestBatsman + '\'' +
                ", bestBowler='" + bestBowler + '\'' +
                '}';
    }
}
