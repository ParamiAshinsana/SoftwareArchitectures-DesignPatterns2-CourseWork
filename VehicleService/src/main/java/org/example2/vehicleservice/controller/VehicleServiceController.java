package org.example2.vehicleservice.controller;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/vehicle")
public class VehicleServiceController {

    @GetMapping("/userService")
    public String getDetail(){
        return "Hello";
    }
}
