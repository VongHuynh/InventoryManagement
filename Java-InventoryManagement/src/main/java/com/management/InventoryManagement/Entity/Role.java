package com.management.InventoryManagement.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class Role implements Serializable {
    public Role(String roleID) {
        this.roleID = roleID;
    }

    @Id
    @Column(length = 20)
    private String roleID;
    @Column(name = "nameRole", nullable = false)
    private String nameRole;
    @JsonIgnore
    @OneToMany(mappedBy = "role", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Authority> authorities;
}
