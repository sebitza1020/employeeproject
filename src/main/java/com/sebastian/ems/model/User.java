package com.sebastian.ems.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable=false)
    private String name;

    @NotNull
    @Column(nullable=false, unique=true)
    private String email;

    @NotNull
    @Column(nullable=false)
    private String password;

    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "date_birth")
    private Date dateBirth;

    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinTable(
            name="users_roles",
            joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="ID")},
            inverseJoinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="ID")})
    private List<Role> roles = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "position")
    private Position position;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "department")
    private Department department;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_contract")
    private EmployeeContract employeeContract;

    @NotNull
    @Column(name = "contract_start")
    private Date contractStart;

    @NotNull
    @Column(name = "contract_end")
    private Date contractEnd;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="address")
    private Address address;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="passport")
    private Passport passport;
}
