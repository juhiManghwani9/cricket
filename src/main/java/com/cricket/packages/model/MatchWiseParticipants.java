package com.cricket.packages.model;

public class MatchWiseParticipants {

    private String fullName;
    private String totalPoints;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(String totalPoints) {
        this.totalPoints = totalPoints;
    }

    @Override
    public String toString() {
        return "MatchWiseParticipants{" +
                "fullName='" + fullName + '\'' +
                ", totalPoints='" + totalPoints + '\'' +
                '}';
    }
}
