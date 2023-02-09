package com.sebastian.ems.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "education")
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="employee_id")
    private User user;

    @NotNull
    @Column(name = "education_type")
    private String educationType;

    @NotNull
    @Column(name = "educational_institution")
    private String educationalInstitution;

    @NotNull
    @Column(name = "diploma_qualification")
    private String diplomaQualification;

    @NotNull
    @Column(name = "diploma_specialty")
    private String diplomaSpecialty;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="form_of_study")
    private StudyForm studyForm;

    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "start_date")
    private String startDate;

    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "end_date")
    private String endDate;

    @NotNull
    @Column(name = "diploma")
    private String diploma;
}
