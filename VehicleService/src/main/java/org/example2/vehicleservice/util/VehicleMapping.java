package org.example2.vehicleservice.util;

import lombok.RequiredArgsConstructor;
import org.example2.vehicleservice.dto.VehicleDTO;
import org.example2.vehicleservice.entity.VehicleEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class VehicleMapping {
     private final ModelMapper modelMapper;

     public VehicleDTO toVehicleDTO(VehicleEntity vehicleEntity) {
          return  modelMapper.map(vehicleEntity, VehicleDTO.class);
     }
     public VehicleEntity toVehicle(VehicleDTO vehicleDTO) {
          return  modelMapper.map(vehicleDTO, VehicleEntity.class);
     }
     public List<VehicleDTO> toVehicleDTOList(List<VehicleEntity> vehicleEntities) {
          return modelMapper.map(vehicleEntities, List.class);
     }
}
