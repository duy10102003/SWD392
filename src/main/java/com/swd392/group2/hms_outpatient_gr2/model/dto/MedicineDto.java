package com.swd392.group2.hms_outpatient_gr2.model.dto;

import com.swd392.group2.hms_outpatient_gr2.model.entity.Medicine;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MedicineDto implements Serializable {
    @NotBlank(message = "Name is mandatory")
    @Size(max = 100, message = "Name must be less than 100 characters")
    private String name;

    @Min(value = 0, message = "Quantity must be zero or greater")
    private int quantity;

    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero")
    private double price;

    @NotBlank(message = "Description is mandatory")
    @Size(max = 255, message = "Description must be less than 255 characters")
    private String description;

    private boolean isActive;

    public void loadFromEntity(Medicine entity) {
        this.name = entity.getName();
        this.quantity = entity.getQuantity();
        this.price = entity.getPrice();
        this.description = entity.getDescription();
        this.isActive = entity.isActive();
    }
}