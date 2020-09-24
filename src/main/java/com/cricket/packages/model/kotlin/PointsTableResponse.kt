package com.cricket.packages.model.kotlin

import com.cricket.packages.persistence.kotlin.PointsTable

class PointsTableResponse {

    var message: String = ""
     var status: String = ""
     var description:String = ""
     var pointsTable: List<PointsTable> = ArrayList()
}
