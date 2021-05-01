package me.kimchidev.springsecondapilogin.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "security")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue
    @Column(name = "index_key")
    Long id;

    @Column(unique = true, length = 50)
    String memberId;

    @Column(length = 500)
    String memberPassword;

    @Column(length = 30)
    String userName;


    LocalDateTime createdAt;

    @Builder
    public Member(String memberId, String memberPassword, String userName){
        this.memberId = memberId;
        this.memberPassword = memberPassword;
        this.userName = userName;
        this.createdAt = LocalDateTime.now();
    }

}
