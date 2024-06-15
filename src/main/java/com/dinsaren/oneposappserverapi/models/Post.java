package com.dinsaren.oneposappserverapi.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "posts")
@DynamicUpdate()
@Data
public class Post extends BaseEntity {
    private static final long serialVersionUID = 4489397646584896516L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String description;
    @Column(name = "total_view")
    private int totalView;
    private String status;

    private String image;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
