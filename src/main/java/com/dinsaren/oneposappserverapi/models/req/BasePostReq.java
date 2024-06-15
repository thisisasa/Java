package com.dinsaren.oneposappserverapi.models.req;


import lombok.Data;

@Data
public class BasePostReq {
    private Integer limit;
    private Integer page;
    private Integer userId;
    private String status;

    private Integer id;

    private Integer categoryId;

    private String name;
}
