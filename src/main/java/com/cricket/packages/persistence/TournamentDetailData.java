package com.cricket.packages.persistence;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;

@Data
@Document(collection = "tournament_details")
public class TournamentDetailData {

    @JsonProperty("tournamentId")
    @Field("_id")
    private Integer tournament_id;

    @JsonProperty("tournamentType")
    @Field("tournament_type")
    List<String> tournament_type;

    @JsonProperty("description")
    @Field("description")
    String description;

    @JsonProperty("startDate")
    @Field("start_date")
    Date start_date;

    @JsonProperty("endDate")
    @Field("end_date")
    Date end_date;

    @JsonProperty("pointsTable")
    @Field("points_table")
    boolean points_table;

    public Integer getTournament_id() {
        return tournament_id;
    }

    public void setTournament_id(Integer tournament_id) {
        this.tournament_id = tournament_id;
    }

    public List<String> getTournament_type() {
        return tournament_type;
    }

    public void setTournament_type(List<String> tournament_type) {
        this.tournament_type = tournament_type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public boolean isPoints_table() {
        return points_table;
    }

    public void setPoints_table(boolean points_table) {
        this.points_table = points_table;
    }
}
