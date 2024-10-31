package com.swd392.group2.hms_outpatient_gr2.model.entity;

import com.swd392.group2.hms_outpatient_gr2.model.dto.MedicineDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.io.Serializable;
import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "medicine")
public class Medicine implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(nullable = false)
    @Nationalized
    private String name;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false, length = 500)
    @Nationalized
    private String description;

    @Column(nullable = false)
    private boolean isActive;

    @OneToMany(mappedBy = "medicine", cascade = CascadeType.ALL)
    private Collection<MedicineItem> medicineItems;

    public void loadFromDto(MedicineDto dto) {
        this.name = dto.getName();
        this.quantity = dto.getQuantity();
        this.price = dto.getPrice();
        this.description = dto.getDescription();
        this.isActive = dto.isActive();
    }
}
