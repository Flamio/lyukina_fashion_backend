package com.mmenshikov.lyukinafashion.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "order_data")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String phone;
    private String address;
    private String email;

    @Column(name = "size_id")
    private Long sizeId;

    @ManyToOne
    @JoinColumn(name = "size_id", nullable = false, updatable = false)
    private Size size;


    private String contactType;
}
