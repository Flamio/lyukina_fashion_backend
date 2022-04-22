package com.mmenshikov.lyukinafashion.product.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "size")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Size {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "size")
    private List<ProductSize> productSizes;
}
