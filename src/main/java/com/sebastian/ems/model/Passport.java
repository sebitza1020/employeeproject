package com.sebastian.ems.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "passports")
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name = "series")
    private String series;

    @NotNull
    @Column(name = "number")
    private String number;

    @NotNull
    @Column(name = "issue_date")
    private Date issueDate;

    @NotNull
    @Column(name = "issue_place")
    private String issuePlace;

    @NotNull
    @Column(name = "registration_place")
    private String registrationPlace;
}
