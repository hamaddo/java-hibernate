package ru.nstu.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import ru.nstu.util.OwnershipType;

import java.util.List;

@Entity
@Getter
@Setter
@Table
@NoArgsConstructor
public class Employer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private OwnershipType ownershipType;

    private String address;

    private String phone;

    private Integer registryNumber;

    @OneToMany(mappedBy = "employer", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Offer> offers;

    public Employer(String name, OwnershipType ownershipType, String address, String phone, Integer registryNumber) {
        this.name = name;
        this.ownershipType = ownershipType;
        this.address = address;
        this.phone = phone;
        this.registryNumber = registryNumber;
    }
}
