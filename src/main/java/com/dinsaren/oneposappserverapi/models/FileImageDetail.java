package com.dinsaren.oneposappserverapi.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "image_details")
@DynamicUpdate()
public class FileImageDetail implements Serializable {

    private static final long serialVersionUID = 4489397646584896516L;
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "FILE_PATH")
    private String filePath;
    @Column(name = "FILE_TYPE")
    private String fileType;
    @Column(name = "FILE_NAME")
    private String fileName;
    @Column(name = "ORIGINAL_FILE_NAME")
    private String originalFileName;
    @Column(name = "FILE_SIZE")
    private Long fileSize;
    @Column(name = "STATUS")
    private String status;
}
