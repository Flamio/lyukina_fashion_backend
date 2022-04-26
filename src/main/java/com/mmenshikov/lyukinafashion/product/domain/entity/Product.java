package com.mmenshikov.lyukinafashion.product.domain.entity;

import com.mmenshikov.lyukinafashion.category.domain.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private BigDecimal price;

    @Column(name = "main_pic")
    private String mainPicture;

    @Column(name = "big_pics")
    private String bigPictures;

    private String thumbs;

    @Column(name = "is_new")
    private Boolean isNew;

    @Column(name = "page_name")
    private String pageName;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    private String description;

    @OneToMany(mappedBy = "product")
    private List<ProductSize> productSizes;

}
