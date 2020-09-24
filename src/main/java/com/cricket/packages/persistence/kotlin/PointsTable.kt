package com.cricket.packages.persistence.kotlin

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.mongodb.core.index.CompoundIndex
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document(collection = "points_table")
@CompoundIndex(def = "{ 'team_name' : 1 , 'tournament_id' : 1}",unique = true)
data class PointsTable(

        @JsonProperty("teamName")
        @Field("team_name")
        val team_name: String,

        @JsonProperty("tournamentId")
        @Field("tournament_id")
        val tournament_id: Int,

        @JsonProperty("matchesPlayed")
        @Field("matches_played")
        val matches_played: Int,

        @JsonProperty("won")
        @Field("won")
        val won: Int,

        @JsonProperty("lost")
        @Field("lost")
        val lost: Int,

        @JsonProperty("points")
        @Field("points")
        val points: Int,

        @JsonProperty("runRate")
        @Field("run_rate")
        val run_rate: String
)
