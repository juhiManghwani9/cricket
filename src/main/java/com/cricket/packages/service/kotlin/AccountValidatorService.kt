package com.cricket.packages.service.kotlin

import com.cricket.packages.response.GenericResponse
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Service
import java.lang.Exception

@Service
class AccountValidatorService(val mongoOperations:MongoOperations){

      fun accountValidator(uuid : String) :GenericResponse{

          var genericResponse=GenericResponse()
          try {
              var query = Query(Criteria.where("token").`is`(uuid))
              var update = Update()
              update.set("isVerified", true)
              var updateResult=mongoOperations.updateFirst(query, update, "user_details")
              if(updateResult.matchedCount==1L && updateResult.modifiedCount==1L )
              {
                  genericResponse.message="Account activated Successfully"
                  genericResponse.status="SUCCESS"
              }
              else{
                  genericResponse.message="Invalid token provided"
                  genericResponse.status="FAILURE"

              }
          }
          catch (e:Exception){
              genericResponse.message="Internal Server Error"
              genericResponse.status="FAILURE"

          }

          return genericResponse

    }
}
