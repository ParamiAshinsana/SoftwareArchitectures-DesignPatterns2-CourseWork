package org.example2.vehicleservice.exception;

public class DuplicateVehicleException extends RuntimeException {
    public DuplicateVehicleException(String message) {
        super(message);
    }
}
