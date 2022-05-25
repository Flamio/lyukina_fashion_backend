package com.mmenshikov.lyukinafashion.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

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
}
