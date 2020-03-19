package com.scc.bukusakuonline.user.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Login {
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("message")
    @Expose
    private String message;
//    @SerializedName("data")
//    @Expose
//    private Data data;
    @SerializedName("token")
    @Expose
    private String token;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

//    public Data getData() {
//        return data;
//    }
//
//    public void setData(Data data) {
//        this.data = data;
//    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
