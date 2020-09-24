package com.cricket.packages.repository.pointsTable

import com.cricket.packages.constants.AppConstants
import com.cricket.packages.exception.MongoDBException
import com.cricket.packages.model.kotlin.PointsTableResponse
import com.cricket.packages.persistence.TournamentDetailData
import com.cricket.packages.persistence.kotlin.PointsTable
import com.mongodb.MongoException
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.FindAndModifyOptions
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Repository

@Repository
class PointsTableImpl(private val mongoOperations: MongoOperations): PointsTableDao {

    private val LOGGER = LoggerFactory.getLogger(PointsTableImpl::class.java)

    override fun getPointsTableFromDb(tournamentId: Int?): PointsTableResponse {
        var latestId = tournamentId
        var response = PointsTableResponse()

        try {
            if (tournamentId == null) {
                LOGGER.info("tournament id is null")
                var query = Query(Criteria.where("points_table").`is`(true)).with(Sort.by(Sort.Direction.DESC, "start_date"))
                var data = mongoOperations.findOne(query, TournamentDetailData::class.java, "tournament_details")
                latestId = data.tournament_id
                response.description = data.description
            }

            LOGGER.info("tournament id is {}", tournamentId)
            val query = Query(Criteria.where("tournament_id").`is`(latestId)).with(Sort.by(Sort.Direction.DESC,"points"))
            val pointsTable: List<PointsTable> = mongoOperations.find(query, PointsTable::class.java, "points_table")

            LOGGER.info("Points table data fetched successfully for ID - {}", tournamentId)

            response.status = AppConstants.SUCCESS
            response.pointsTable = pointsTable
            response.message = "Data Fetched successfully"
            return response

        } catch (e: MongoException) {
            LOGGER.error("failed to fetch tournament data for ID - {}", tournamentId)
            throw MongoDBException("Failed to insert feedback");
        } catch (e: Exception) {
            LOGGER.error("failed to fetch tournament data for ID - {}", tournamentId)
            throw Exception(e.message);
        }
    }

    override fun updateTeams(pointsTable: List<PointsTable>) {
        try {
            for (ptsTable in pointsTable) {

                val query = Query(Criteria.where("tournament_id").`is`(ptsTable.tournament_id).and("team_name").`is`(ptsTable.team_name))

                var update = Update()
                update.set("matches_played", ptsTable.matches_played)
                update.set("won", ptsTable.won)
                update.set("lost", ptsTable.lost)
                update.set("points", ptsTable.points)
                update.set("run_rate", ptsTable.run_rate)

                mongoOperations.findAndModify(query, update, FindAndModifyOptions.options().upsert(true), PointsTable::class.java, "points_table")
                LOGGER.info("team data updated successfully - {}", ptsTable.tournament_id)
            }
        }
        catch (e: MongoException) {
            LOGGER.error("failed to update points table data")
            throw MongoDBException("Failed to insert feedback");
        } catch (e: Exception) {
            LOGGER.error("failed to update points table data")
            throw Exception(e.message);
        }
    }
}
