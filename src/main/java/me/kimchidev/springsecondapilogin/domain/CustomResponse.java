package me.kimchidev.springsecondapilogin.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
public class CustomResponse {

    private String serverCode;
    private String serverMsg;

    public void createResponse(String code, String msg){
        this.serverCode = code;
        this.serverMsg = msg;
    }
}
