package com.cricket.packages.persistence;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collation = "feedback")
public class Feedback {

    @JsonProperty("rating")
    @Field("rating")
    private Integer rating;

    @JsonProperty("feedbackMessage")
    @Field("feedback_message")
    private String feedbackMessage;

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
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
        return "Feedback{" +
                "rating=" + rating +
                ", feedbackMessage='" + feedbackMessage + '\'' +
                '}';
    }
}
