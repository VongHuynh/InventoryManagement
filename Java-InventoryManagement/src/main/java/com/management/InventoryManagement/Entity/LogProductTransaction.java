package com.management.InventoryManagement.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "LogProductTransaction")
public class LogProductTransaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LogProductTransID")
    private Integer LogProductTransID;
    @Column(name = "dateLog", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateLog;
    @ManyToOne
    @JoinColumn(name = "productTransID")
    private ProductTransaction productTransID;
    @ManyToOne
    @JoinColumn(name = "userID")
    private UserAccount userID;
}
