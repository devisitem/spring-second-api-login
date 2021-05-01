package me.kimchidev.springsecondapilogin.service;

import lombok.RequiredArgsConstructor;
import me.kimchidev.springsecondapilogin.domain.AuthenticationRequest;
import me.kimchidev.springsecondapilogin.domain.Member;
import me.kimchidev.springsecondapilogin.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void createMember(AuthenticationRequest authReq){
        Member member = Member.builder()
                .memberId(authReq.getPrinciple())
                .memberPassword(authReq.getCredential())
                .userName("Kimchi")
                .build();

    }
}
