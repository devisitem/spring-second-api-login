package me.kimchidev.springsecondapilogin.service;

import lombok.RequiredArgsConstructor;
import me.kimchidev.springsecondapilogin.domain.AuthenticationRequest;
import me.kimchidev.springsecondapilogin.domain.Member;
import me.kimchidev.springsecondapilogin.domain.MemberDetails;
import me.kimchidev.springsecondapilogin.repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Member member = memberRepository.findById(userId);
        System.out.println("[MemberService.loadUserByUsername] : ###");
        if(member == null) {
            throw new UsernameNotFoundException("존재하지 않는 아이디입니다.");
        }

        return new MemberDetails(member);
    }

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
