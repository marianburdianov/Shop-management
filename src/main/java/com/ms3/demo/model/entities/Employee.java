package com.ms3.demo.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Setter
@Getter
@RequiredArgsConstructor
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(generator = "increment")
    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "gender")
    private String gender;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "hire_date")
    private LocalDate hireDate;

    @Column(name = "role")
    private String role;

    @Column(name = "salary")
    private Double salary;

    @OneToMany(fetch = FetchType.EAGER,
            mappedBy = "employee",
            cascade = CascadeType.ALL)
    @JsonBackReference("employees")
    private List<PurchaseInfo> purchaseInfoList;
}
