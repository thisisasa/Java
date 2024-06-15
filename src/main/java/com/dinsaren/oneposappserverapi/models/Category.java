package com.dinsaren.oneposappserverapi.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "categories")
@DynamicUpdate()
@Data
public class Category extends BaseEntity {
    private static final long serialVersionUID = 4489397646584896516L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String imageUrl;
    private String status;

}
