package com.ms3.demo.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Setter
@Getter
@RequiredArgsConstructor
@Table(name = "customers")
public class Customer {
    
    @Id
    @GeneratedValue(generator = "increment")
    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private Long phoneNumber;

    @Column(name = "address")
    private String address;

    @OneToMany(fetch = FetchType.EAGER,
            mappedBy = "customer",
            cascade = CascadeType.ALL)
    @JsonBackReference("customers")
    private List<PurchaseInfo> purchaseInfoList;
}
