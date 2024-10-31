package com.swd392.group2.hms_outpatient_gr2.model.entity;

import com.swd392.group2.hms_outpatient_gr2.model.dto.DepartmentDto;
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
@Table(name = "department")
public class Department implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(nullable = false)
    @Nationalized
    private String name;

    @Column(nullable = false)
    private boolean isActive;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private Collection<Account> accounts;

    public void loadFromDto(DepartmentDto dto) {
        this.name = dto.getName();
        this.isActive = dto.isActive();
    }
}
