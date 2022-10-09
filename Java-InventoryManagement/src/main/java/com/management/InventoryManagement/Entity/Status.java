package com.management.InventoryManagement.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "status")
public class Status {
    public Status(Integer statusID) {
        this.statusID = statusID;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "statusID")
    private Integer statusID;
    @Column(nullable = false)
    private String statusName;
    @JsonIgnore
    @OneToMany(mappedBy = "status", fetch = FetchType.LAZY)
    private List<ProductTransaction> productTransactions;
}
