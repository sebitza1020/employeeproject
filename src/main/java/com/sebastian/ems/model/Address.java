package com.sebastian.ems.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "country")
    private String country;

    @Column(name = "county")
    private String county;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "apartment_number")
    private Integer apartmentNumber;
}
