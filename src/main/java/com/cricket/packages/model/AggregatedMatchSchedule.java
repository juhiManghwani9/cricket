package com.cricket.packages.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AggregatedMatchSchedule {

    private String team1;
    private String team2;
    private String venue;
    private Date date;
    private String match_description;
    private String match_winner;
    private String batsman_winner;
    private String bowler_winner;

    public void setMatch_description(String match_description) {
        this.match_description = match_description;
    }

    public String getMatch_description() {
        return match_description;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMatch_winner() {
        return match_winner;
    }

    public void setMatch_winner(String match_winner) {
        this.match_winner = match_winner;
    }

    public String getBatsman_winner() {
        return batsman_winner;
    }

    public void setBatsman_winner(String batsman_winner) {
        this.batsman_winner = batsman_winner;
    }

    public String getBowler_winner() {
        return bowler_winner;
    }

    public void setBowler_winner(String bowler_winner) {
        this.bowler_winner = bowler_winner;
    }
}
