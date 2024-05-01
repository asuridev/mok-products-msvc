package com.suarezr.products_msvc.product.persistence.entities;

import com.suarezr.products_msvc.category.persistence.entities.CategoryEntity;
import com.suarezr.products_msvc.common.audit.AuditableEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;

@EntityListeners({AuditingEntityListener.class})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="products")
@Entity
public class ProductEntity extends AuditableEntity {

    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private String id;

    private String name;

    private String description;

    private Integer stock;

    private BigDecimal price;

    @JoinColumn(name="category_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private CategoryEntity category;
}

  