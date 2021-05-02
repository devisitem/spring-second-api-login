package me.kimchidev.springsecondapilogin.service;

import lombok.RequiredArgsConstructor;
import me.kimchidev.springsecondapilogin.domain.AuthenticationRequest;
import me.kimchidev.springsecondapilogin.domain.Member;
import me.kimchidev.springsecondapilogin.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void createMember(AuthenticationRequest authReq){
        Member member = Member.builder()
                .memberId(authReq.getPrincipal())
                .memberPassword(passwordEncoder.encode(authReq.getCredentials()))
                .userName("Kimchi")
                .build();

        memberRepository.save(member);
    }

    public Member verifyMember(AuthenticationRequest authReq){
        Member found = memberRepository.findById(authReq.getPrincipal());

        if(passwordEncoder.matches(authReq.getCredentials(),found.getMemberPassword())){
            return found;
        }else{
            return null;
        }
    }
}
