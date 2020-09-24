package com.cricket.packages.controller

import com.cricket.packages.constants.AppConstants
import com.cricket.packages.model.kotlin.PointsTableResponse
import com.cricket.packages.persistence.kotlin.PointsTable
import com.cricket.packages.response.GenericResponse
import com.cricket.packages.service.kotlin.PointsTableService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/points-table")
class PointsTableController(val pointsTableService:PointsTableService) {

    @GetMapping
    fun getPointsTableOfTournamentID(@RequestParam(required = false) tournamentId:Int?):ResponseEntity<PointsTableResponse> {

        var response = pointsTableService.getPointsTable(tournamentId)
        return if(AppConstants.SUCCESS == response.status) {
            ResponseEntity(response, HttpStatus.OK)
        }
        else ResponseEntity(response,HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @PostMapping
    fun updateTeamData(@RequestBody request:List<PointsTable>):ResponseEntity<GenericResponse> {
        var response = pointsTableService.updateNewTeam(request)
        return if(AppConstants.SUCCESS.equals(response.message,ignoreCase = false)) {
            ResponseEntity(response, HttpStatus.OK)
        }
        else ResponseEntity(response,HttpStatus.INTERNAL_SERVER_ERROR)
    }

}
