package com.cmd.farmlinkapi
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import groovy.json.JsonOutput

@RestController
@RequestMapping("/health")
class HealthCheckController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    String healthCheck() {
        Map<String, String> response =  [status: "OK"]
        JsonOutput.toJson(response)
    }
}