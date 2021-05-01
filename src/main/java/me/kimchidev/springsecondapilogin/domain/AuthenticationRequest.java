package me.kimchidev.springsecondapilogin.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Setter
@Getter
public class AuthenticationRequest {
    @NotEmpty
    String principle;
    @NotEmpty
    String credential;

}
