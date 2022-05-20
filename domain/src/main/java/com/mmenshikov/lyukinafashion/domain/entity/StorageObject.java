package com.mmenshikov.lyukinafashion.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "storage_object")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class StorageObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String path;

    @Column(name = "api_path")
    private String apiPath;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", updatable = false, insertable = false)
    private Product product;

    @Column(name = "product_id")
    private Long productId;

    @Enumerated(EnumType.STRING)
    private ProductObjectPurpose purpose;
}
