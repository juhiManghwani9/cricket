package com.cricket.packages.request;

public class FeedbackRequest {
    
    private int rating;
    private String feedbackMessage;

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getFeedbackMessage() {
        return feedbackMessage;
    }

    public void setFeedbackMessage(String feedbackMessage) {
        this.feedbackMessage = feedbackMessage;
    }

    @Override
    public String toString() {
        return "FeedbackRequest{" +
                "rating=" + rating +
                ", feedbackMessage='" + feedbackMessage + '\'' +
                '}';
    }
}
