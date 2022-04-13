package com.mmenshikov.lyukinafashion.category.domain.entity;

import com.mmenshikov.lyukinafashion.product.domain.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "category")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "is_new")
    private Boolean isNew;

    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
