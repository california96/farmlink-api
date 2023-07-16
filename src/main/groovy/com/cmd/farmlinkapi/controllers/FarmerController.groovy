package com.cmd.farmlinkapi.controllers

import com.cmd.farmlinkapi.models.Farmer
import com.cmd.farmlinkapi.services.FarmerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

import java.util.concurrent.ExecutionException

@RestController
class FarmerController {

    @Autowired
    FarmerService farmerService

    @GetMapping("/farmer")
    Farmer getFarmer(@RequestParam String name) throws InterruptedException, ExecutionException{
        return farmerService.getFarmerbyName(name)
    }
}
