package com.cricket.packages.model;

import java.util.List;

public class Players {

    private String team_name;
    private List<String> players;

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public List<String> getPlayers() {
        return players;
    }

    public void setPlayers(List<String> players) {
        this.players = players;
    }

    public Players(String team_name, List<String> players) {
        this.team_name = team_name;
        this.players = players;
    }
    public Players() {

    }
}
