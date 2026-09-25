package com.evplatform.user_service.dto;

import com.evplatform.user_service.entity.ConnectorType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class UpdateVehicleRequest {

    @NotBlank(message = "Registration number is required")
    @Size(max = 20)
    private String registrationNumber;

    @NotBlank(message = "Brand is required")
    @Size(max = 50)
    private String brand;

    @NotBlank(message = "Model is required")
    @Size(max = 50)
    private String model;

    @NotNull(message = "Battery capacity is required")
    @DecimalMin(
            value = "1.0",
            message = "Battery capacity must be greater than 0"
    )
    private BigDecimal batteryCapacity;

    @NotNull(message = "Connector type is required")
    private ConnectorType connectorType;
}