package com.management.InventoryManagement.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "UserAccount")
@SQLDelete(sql = "UPDATE user_account SET is_deleted = true WHERE userid=?")
@FilterDef(name = "deletedUserAccountFilter", parameters = @ParamDef(name = "isDeleted", type = "boolean"))
@Filter(name = "deletedUserAccountFilter", condition = "isDeleted = :isDeleted")
//@Where(clause = "is_deleted=false")
public class UserAccount implements Serializable {
    public UserAccount(Integer userID) {
        this.userID = userID;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userID;
    @Column(name = "username", nullable = false)
    private String userName;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "fullName", nullable = false)
    private String fullName;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "phoneNumber", nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private boolean isDeleted = Boolean.FALSE;
    @JsonIgnore
    @OneToMany(mappedBy = "userAccount", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Authority> authorities;
    @JsonIgnore
    @OneToMany(mappedBy = "userID", fetch = FetchType.LAZY)
    private List<LogProductTransaction> logProductTransactions;
}
