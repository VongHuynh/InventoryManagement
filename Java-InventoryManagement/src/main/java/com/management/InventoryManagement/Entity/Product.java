package com.management.InventoryManagement.Entity;



import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
@SQLDelete(sql = "UPDATE product SET is_deleted = true WHERE productID=?")
@FilterDef(name = "deletedProductFilter", parameters = @ParamDef(name = "isDeleted", type = "boolean"))
@Filter(name = "deletedProductFilter", condition = "isDeleted = :isDeleted")
public class Product implements Serializable {
    public Product(Integer productId) {
        this.productId = productId;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productID")
    private Integer productId;
    @Column(nullable = false)
    private String title;
    private String description;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private Integer amount;
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date importDate;
    @Column
    private boolean isDeleted = Boolean.FALSE;
    @ManyToOne
    @JoinColumn(name = "categoriesID")
    private Category category;
    @JsonIgnore
    @OneToMany(mappedBy = "productIMG", fetch = FetchType.LAZY)
    private List<ProductImage> productImages;
    @JsonIgnore
    @OneToMany(mappedBy = "productID", fetch = FetchType.LAZY)
    private List<ProductTransaction> productTransactions;
}
