package com.ms3.demo.model.entities;

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
@Table(name = "purchaseinfos")
public class PurchaseInfo {

    @Id
    @GeneratedValue(generator = "increment")
    @Column(name = "purchaseinfo_id")
    private Long purchaseInfoId;

    @Column(name = "cost")
    private Double cost;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "date")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "purchaseinfos",
            joinColumns = @JoinColumn(name = "purchaseinfo_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> productList;
}
