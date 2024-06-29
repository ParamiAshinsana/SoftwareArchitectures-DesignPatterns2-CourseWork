package org.example2.vehicleservice.controller;

import lombok.RequiredArgsConstructor;
import org.example2.vehicleservice.dto.VehicleDTO;
import org.example2.vehicleservice.service.VehicleService;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/vehicle")
public class VehicleServiceController {
    private final VehicleService vehicleService;

    @GetMapping("/vehicleService")
    public String getDetail(){
        return "Hello Vehicle";
    }
    @PostMapping(value = "/saveVehicle")
    public VehicleDTO saveVehicle(@RequestBody VehicleDTO vehicleDTO){
        return vehicleService.saveVehicle(vehicleDTO);
    }

    @PutMapping(value = "/updateVehicle/{id}")
    public void updateVehicle(@RequestBody VehicleDTO vehicleDTO, @PathVariable ("id") String id){
        vehicleService.updateVehicle(id,vehicleDTO);
        System.out.println("User Updated!");
    }

    @DeleteMapping(value = "/deleteVehicle/{id}")
    public void deleteVehicle(@PathVariable ("id") String id){
        vehicleService.deleteVehicle(id);
    }

    @GetMapping(value = "/getAllVehicles")
    List<VehicleDTO> getAllVehicles(){
        return vehicleService.getAllVehicles();
    }
}
