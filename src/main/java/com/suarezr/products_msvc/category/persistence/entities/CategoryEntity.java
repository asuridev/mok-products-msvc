package com.suarezr.products_msvc.category.persistence.entities;

import com.suarezr.products_msvc.common.audit.AuditableEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners({AuditingEntityListener.class})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="categories")
@Entity
public class CategoryEntity extends AuditableEntity {

    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private String id;

    private String name;

}

  