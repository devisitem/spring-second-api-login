package me.kimchidev.springsecondapilogin.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.kimchidev.springsecondapilogin.domain.AuthenticationRequest;
import me.kimchidev.springsecondapilogin.domain.CustomResponse;
import me.kimchidev.springsecondapilogin.domain.Member;
import me.kimchidev.springsecondapilogin.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/user",produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final AuthenticationManager authenticationManager;
    private CustomResponse customRes;


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationRequest authReq, Errors error) throws Exception{

        log.info("[ #START# POST /user/login ] : {}",authReq );

        Member member = memberService.verifyMember(authReq);


        Authentication token = new UsernamePasswordAuthenticationToken(member.getMemberId(),member.getMemberPassword());
        log.info("token : {}",token);


        Authentication authentication = authenticationManager.authenticate(token);
        log.info("authentication : {}",authentication);


        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.info("SecurityContextHolder.getContext() : {}",SecurityContextHolder.getContext().getAuthentication());




        customRes = new CustomResponse();
        if(member != null){
            log.info("member : {}",member);
            customRes.createResponse("LOGIN_SUCCESS","환영합니다. "+member.getUserName());
        } else {
            customRes.createResponse("LOGIN_FAILED","등록되지 않은 정보이거나 일치하지않습니다.");
        }

        log.info("[ #END# POST /user/login ]");
        return new ResponseEntity<>(customRes, HttpStatus.OK);
    }

    @PostMapping("/join")
    public ResponseEntity join(@RequestBody @Valid AuthenticationRequest authReq, Errors error) throws Exception{

        log.info("[ #START# POST /user/join ] : {}",authReq );

        memberService.createMember(authReq);




        customRes = new CustomResponse();
        customRes.createResponse("JOIN_SUCCESS","가입에 성공 하였습니다.");

        log.info("[ #END# POST /user/join ]");
        return new ResponseEntity<>(customRes, HttpStatus.OK);
    }

}
