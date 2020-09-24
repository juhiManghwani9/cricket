package com.cricket.packages.model;

import com.cricket.packages.persistence.UserMatchDetail;

import java.util.Date;

public class PredictedMatches {

    private int tournament_id;
    private int match_id;
    private String team1;
    private String team2;
    private String venue;
    private Date date;
    private String match_description;


    public String getMatch_description() {
        return match_description;
    }

    public void setMatch_description(String match_description) {
        this.match_description = match_description;
    }

    public int getTournament_id() {
        return tournament_id;
    }

    public void setTournament_id(int tournament_id) {
        this.tournament_id = tournament_id;
    }

    public int getMatch_id() {
        return match_id;
    }

    public void setMatch_id(int match_id) {
        this.match_id = match_id;
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

    @Override
    public String toString() {
        return "PredictedMatches{" +
                "tournament_id=" + tournament_id +
                ", match_id=" + match_id +
                ", team1='" + team1 + '\'' +
                ", team2='" + team2 + '\'' +
                ", venue='" + venue + '\'' +
                ", date=" + date +
                ", match_description='" + match_description + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o){
        if(o instanceof UserMatchDetail) {
            UserMatchDetail a=(UserMatchDetail) o;
            if(this.tournament_id==a.getTournamentId() && this.match_id==a.getMatchId())
                return true;

        }

        return false;

    }
}
