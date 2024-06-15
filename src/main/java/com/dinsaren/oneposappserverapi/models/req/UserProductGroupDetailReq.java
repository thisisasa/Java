package com.dinsaren.oneposappserverapi.models.req;

import com.dinsaren.oneposappserverapi.models.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class UserProductGroupDetailReq extends BaseEntity{
    private Integer id;
    private Integer productUnitId;
    private Integer productGroupId;
    private String status;
}