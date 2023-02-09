package com.sebastian.ems.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

public class FamilyMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name="employee_id")
    private User user;

    @NotNull
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "birthdate")
    private String birthdate;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="kinship_degree")
    private KinshipDegree kinshipDegree;
}
