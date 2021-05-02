package me.kimchidev.springsecondapilogin.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Setter
@Getter
@ToString
public class AuthenticationRequest {
    @NotEmpty
    String principal;
    @NotEmpty
    String credentials;

}
