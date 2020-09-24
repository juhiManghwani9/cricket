package com.cricket.packages.util;

public class AccuracyData {

    private float bowlerWinner;
    private float matchWinner;
    private float batsmanWinner;

    public float getBowlerWinner() {
        return bowlerWinner;
    }

    public void setBowlerWinner(float bowlerWinner) {
        this.bowlerWinner = bowlerWinner;
    }

    public float getMatchWinner() {
        return matchWinner;
    }

    public void setMatchWinner(float matchWinner) {
        this.matchWinner = matchWinner;
    }

    public float getBatsmanWinner() {
        return batsmanWinner;
    }

    public void setBatsmanWinner(float batsmanWinner) {
        this.batsmanWinner = batsmanWinner;
    }

    @Override
    public String toString() {
        return "AccuracyData{" +
                "bowlerWinner=" + bowlerWinner +
                ", matchWinner=" + matchWinner +
                ", batsmanWinner=" + batsmanWinner +
                '}';
    }
}
