package com.cricket.packages.request;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class MatchScheduleRequest {

    @NotNull
    private Integer tournament_id;
    @NotNull
    private Integer match_id;
    @NotNull
    private String team1;
    @NotNull
    private String team2;
    @NotNull
    private String venue;
    @NotNull
    private String match_description;
    @NotNull
    private Date date;

    public String getMatch_description() {
        return match_description;
    }

    public void setMatch_description(String match_description) {
        this.match_description = match_description;
    }

    public Integer getTournament_id() {
        return tournament_id;
    }

    public void setTournament_id(int tournament_id) {
        this.tournament_id = tournament_id;
    }

    public Integer getMatch_id() {
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
        return "MatchScheduleRequest{" +
                "tournament_id=" + tournament_id +
                ", match_id=" + match_id +
                ", team1='" + team1 + '\'' +
                ", team2='" + team2 + '\'' +
                ", venue='" + venue + '\'' +
                ", match_description='" + match_description + '\'' +
                ", date=" + date +
                '}';
    }
}
