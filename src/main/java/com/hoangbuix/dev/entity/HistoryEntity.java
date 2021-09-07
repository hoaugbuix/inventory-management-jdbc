package com.hoangbuix.dev.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "history")
@Table(name = "history")
public class HistoryEntity extends BaseEntity {
    @Column(name = "action_name")
    private String actionName;

    @Column(name = "type")
    private int type;

    @Column(name = "qty")
    private int qty;

    @Column(name = "price")
    private BigDecimal price;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductInfoEntity productInfo;
}
