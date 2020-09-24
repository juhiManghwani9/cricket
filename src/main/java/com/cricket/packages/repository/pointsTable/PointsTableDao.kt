package com.cricket.packages.repository.pointsTable

import com.cricket.packages.model.kotlin.PointsTableResponse
import com.cricket.packages.persistence.kotlin.PointsTable

interface PointsTableDao {

    fun getPointsTableFromDb(tournamentId:Int?): PointsTableResponse

    fun updateTeams(pointsTable: List<PointsTable>)
}
