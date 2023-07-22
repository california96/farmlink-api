package com.cmd.farmlinkapi.controllers
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
@RestController
class SetupController {

    @GetMapping
    Map<String, String> getDefaultResponse() {
        Map<String, String> response = new HashMap<>()
        response.put("message", "Hello, this is the default JSON response for Farmlink API. If you are seeing this, you got the setup correctly.")
        response
    }
}
