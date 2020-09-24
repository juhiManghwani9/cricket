package com.cricket.packages.persistence;

import com.cricket.packages.util.AccuracyData;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "match_schedule")
public class  MatchScheduleData {

    private int tournament_id;
    private int match_id;
    private String team1;
    private String team2;
    private String venue;
    private Date date;
    private String batsman_winner;
    private String match_winner;
    private String bowler_winner;
    private AccuracyData accuracy_data;
    private String match_description;

    public String getMatch_description() {
        return match_description;
    }

    public void setMatch_description(String match_description) {
        this.match_description = match_description;
    }

    public AccuracyData getAccuracy_data() {
        return accuracy_data;
    }

    public void setAccuracy_data(AccuracyData accuracy_data) {
        this.accuracy_data = accuracy_data;
    }

    public int getTournament_id() {
        return tournament_id;
    }

    public void setTournament_id(int tournament_id) {
        this.tournament_id = tournament_id;
    }

    public int getMatchId() {
        return match_id;
    }

    public void setMatchId(int matchId) {
        this.match_id = matchId;
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

    public int getMatch_id() {
        return match_id;
    }

    public void setMatch_id(int match_id) {
        this.match_id = match_id;
    }

    public String getBatsman_winner() {
        return batsman_winner;
    }

    public void setBatsman_winner(String batsman_winner) {
        this.batsman_winner = batsman_winner;
    }

    public String getMatch_winner() {
        return match_winner;
    }

    public void setMatch_winner(String match_winner) {
        this.match_winner = match_winner;
    }

    public String getBowler_winner() {
        return bowler_winner;
    }

    public void setBowler_winner(String bowler_winner) {
        this.bowler_winner = bowler_winner;
    }

    @Override
    public String toString() {
        return "MatchScheduleData{" +
                "tournament_id=" + tournament_id +
                ", match_id=" + match_id +
                ", team1='" + team1 + '\'' +
                ", team2='" + team2 + '\'' +
                ", venue='" + venue + '\'' +
                ", date=" + date +
                ", batsman_winner='" + batsman_winner + '\'' +
                ", match_winner='" + match_winner + '\'' +
                ", bowler_winner='" + bowler_winner + '\'' +
                ", accuracy_data=" + accuracy_data +
                '}';
    }
}
