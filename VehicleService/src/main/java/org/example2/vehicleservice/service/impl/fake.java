//@Service
//@Transactional
//@RequiredArgsConstructor
//public class VehicleServiceIMPL implements VehicleService {
//    private final VehicleMapping vehicleMapping;
//    private final VehicleDAO vehicleDAO;
//
//    @Override
//    public VehicleDTO saveVehicle(VehicleDTO vehicleDTO) {
//        // Check if the vehicleNo already exists
//        if (vehicleDAO.existsByVehicleNo(vehicleDTO.getVehicleNo())) {
//            throw new IllegalArgumentException("Vehicle number already exists");
//        }
//
//        vehicleDTO.setVehicleRegistrationId(UUID.randomUUID().toString());
//        return vehicleMapping.toVehicleDTO(vehicleDAO.save(vehicleMapping.toVehicle(vehicleDTO)));
//    }
//
//    @Override
//    public void deleteVehicle(String id) {
//        vehicleDAO.deleteById(id);
//    }
//
//    @Override
//    public void updateVehicleByRegistrationNo(String vehicleNo, VehicleDTO vehicleDTO) {
//        Optional<VehicleEntity> tmpVehicle = vehicleDAO.findByVehicleNo(vehicleNo);
//        if (!tmpVehicle.isPresent()) throw new NotFoundException("Vehicle not found");
//
//        VehicleEntity existingVehicle = tmpVehicle.get();
//        existingVehicle.setVehicleType(vehicleDTO.getVehicleType());
//        existingVehicle.setFuelType(vehicleDTO.getFuelType());
//        existingVehicle.setNameOfOwner(vehicleDTO.getNameOfOwner());
//        existingVehicle.setAddressOfOwner(vehicleDTO.getAddressOfOwner());
//        existingVehicle.setRegisteredDate(vehicleDTO.getRegisteredDate());
//
//        vehicleDAO.save(existingVehicle);
//    }
//
//    @Override
//    public List<VehicleDTO> getAllVehicles() {
//        return vehicleMapping.toVehicleDTOList(vehicleDAO.findAll());
//    }
//
//    @Override
//    public VehicleDTO getSelectedVehicle(String id) {
//        return vehicleDAO.findById(id)
//                .map(vehicleMapping::toVehicleDTO)
//                .orElseThrow(() -> new NotFoundException("Vehicle not found"));
//    }
//}
