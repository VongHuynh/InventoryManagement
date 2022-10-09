package com.management.InventoryManagement.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ProductTransaction")
public class ProductTransaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productTransID")
    private Integer productTransID;
    @Column(nullable = false)
    private Integer amount;
    @ManyToOne
    @JoinColumn(name = "statusID")
    private Status status;
    @ManyToOne
    @JoinColumn(name = "productID")
    private Product productID;
    @JsonIgnore
    @OneToMany(mappedBy = "productTransID", fetch = FetchType.LAZY)
    private List<LogProductTransaction> logProductTransaction;
}
