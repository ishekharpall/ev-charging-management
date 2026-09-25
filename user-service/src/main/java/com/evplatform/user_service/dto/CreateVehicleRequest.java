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
public class CreateVehicleRequest {

    @NotBlank(message = "Registration number is required")
    @Size(
            min = 3,
            max = 20,
            message = "Registration number must be between 3 and 20 characters"
    )
    private String registrationNumber;

    @NotBlank(message = "Brand is required")
    @Size(
            min = 2,
            max = 50,
            message = "Brand must be between 2 and 50 characters"
    )
    private String brand;

    @NotBlank(message = "Model is required")
    @Size(
            min = 1,
            max = 50,
            message = "Model must be between 1 and 50 characters"
    )
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