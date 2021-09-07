package com.hoangbuix.dev.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "product_info")
@Table(name = "product_info")
public class ProductInfoEntity extends BaseEntity {
    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "cate_id")
    private int cateId;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "cate_id")
//    private CategoryEntity categories;

//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "histories")
//    private Set<HistoryEntity> histories;
//
//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "productInStocks")
//    private Set<ProductInStockEntity> productInStocks;
//
//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "invoices")
//    private Set<InvoiceEntity> invoices;

    @Transient
    @JsonIgnore
    private MultipartFile multipartFile;
}
