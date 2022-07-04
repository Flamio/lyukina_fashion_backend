package com.mmenshikov.lyukinafashion.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "product_order")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ProductOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_size_id", updatable = false, insertable = false)
    private ProductSize productSize;

    @Column(name = "product_size_id")
    private Long productSizeId;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "order_info_id")
    private OrderInfo orderInfo;
}
