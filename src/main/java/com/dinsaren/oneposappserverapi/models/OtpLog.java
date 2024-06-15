package com.dinsaren.oneposappserverapi.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "otp_logs")
@DynamicUpdate()
@Data
public class OtpLog extends BaseEntity {
    private static final long serialVersionUID = 4489397646584896516L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String token;
    private String sendTo;
    private String otp;
    private String otpMessage;
    private String status;
    private String actionType;

}