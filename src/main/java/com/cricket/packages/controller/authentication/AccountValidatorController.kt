package com.cricket.packages.controller.authentication

import com.cricket.packages.constants.AppConstants
import com.cricket.packages.service.kotlin.AccountValidatorService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class AccountValidatorController(private var accountValidatorService: AccountValidatorService) {


    @RequestMapping(path = ["/validate/{param}"], method = [RequestMethod.GET],produces = [MediaType.TEXT_PLAIN_VALUE])
    fun validateAccount(@PathVariable("param")  uuid:String):ResponseEntity<String>{

        var response = accountValidatorService.accountValidator(uuid)
        return if(AppConstants.SUCCESS == response.status) {
            ResponseEntity(response.message, HttpStatus.OK)

        }
        else ResponseEntity(response.message, HttpStatus.INTERNAL_SERVER_ERROR)
    }


}
