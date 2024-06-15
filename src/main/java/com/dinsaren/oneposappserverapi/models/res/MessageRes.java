package com.dinsaren.oneposappserverapi.models.res;

import com.dinsaren.oneposappserverapi.constants.Constants;
import com.dinsaren.oneposappserverapi.constants.ErrorCode;

public class MessageRes {
    private String code;
    private String message;
    private String messageKh;
    private Object data;

    public MessageRes() {
    }

    public MessageRes(String code, String message, String messageKh, Object data) {
        this.code = code;
        this.message = message;
        this.messageKh = messageKh;
        this.data = data;
    }

    public MessageRes(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public void setSuccess(Object data) {
        this.code = "SUC-000";
        this.message = "Data Approval Success";
        this.messageKh = "ការអនុម័តទិន្នន័យជោគជ័យ";
        this.data = data;
    }

    public void setInternalServer() {
        this.code = ErrorCode.INTERNAL_SERVER_ERROR;
        this.message = Constants.INTERNAL_SERVER_ERROR;
        this.messageKh = "កំហុសម៉ាស៊ីនមេខាងក្នុង";
    }

    public void setBadRequest() {
        this.code = ErrorCode.BAD_REQUEST;
        this.message = Constants.BAD_REQUEST;
        this.messageKh = "សំណើមិនល្អ";
    }

    public void setError(Object data) {
        this.code = "ERR-000";
        this.message = "Data Approval Error";
        this.messageKh = "ការអនុម័តទិន្នន័យមិនជោគជ័យ";
        this.data = data;
    }

    public void setCreateSuccess(Object data) {
        this.code = "SUC-000";
        this.message = "Create Data Success";
        this.messageKh = "បង្កើតទិន្នន័យជោគជ័យ";
        this.data = data;
    }

    public void setUpdateSuccess(Object data) {
        this.code = "SUC-000";
        this.message = "Update Data Success";
        this.messageKh = "ធ្វើបច្ចុប្បន្នភាពទិន្នន័យជោគជ័យ";
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessageKh() {
        return messageKh;
    }

    public void setMessageKh(String messageKh) {
        this.messageKh = messageKh;
    }
}