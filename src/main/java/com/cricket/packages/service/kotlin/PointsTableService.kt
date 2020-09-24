package com.cricket.packages.service.kotlin

import com.cricket.packages.constants.AppConstants
import com.cricket.packages.model.kotlin.PointsTableResponse
import com.cricket.packages.persistence.kotlin.PointsTable
import com.cricket.packages.repository.pointsTable.PointsTableDao
import com.cricket.packages.response.GenericResponse
import com.cricket.packages.service.FeedbackService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class PointsTableService(val pointsTableDao: PointsTableDao): PointsTableInterface {

    private val LOGGER = LoggerFactory.getLogger(FeedbackService::class.java)

    override fun getPointsTable(tournamentId: Int?): PointsTableResponse {
        try{
            return pointsTableDao.getPointsTableFromDb(tournamentId)
        }
        catch (e: Exception) {
            throw Exception(e.message);
        }
    }

    override fun updateNewTeam(pointsTable: List<PointsTable>): GenericResponse {
        var response = GenericResponse()
        try {
            pointsTableDao.updateTeams(pointsTable)
            response.status = AppConstants.SUCCESS
            response.message = "team points updated successfully"
            return response
           }
        catch (e:Exception) {
            throw Exception(e.message);
        }
    }
}


interface PointsTableInterface {
    fun getPointsTable(tournamentId:Int?): PointsTableResponse
    fun updateNewTeam(pointsTable: List<PointsTable>):GenericResponse

}
