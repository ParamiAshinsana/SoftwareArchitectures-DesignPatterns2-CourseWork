package org.example2.vehicleservice.util;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VehicleMapping {
     private final ModelMapper modelMapper;


}
