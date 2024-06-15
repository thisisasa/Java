package com.dinsaren.oneposappserverapi.models.req;

import lombok.Data;

@Data
public class RegisterVerifyReq {
    private String deviceId;
    private String phoneNumber;
    private String otp;
}
