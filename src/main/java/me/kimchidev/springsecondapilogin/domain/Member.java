package me.kimchidev.springsecondapilogin.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@ToString
@Entity
@Getter
@Table(name = "security_member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue
    @Column(name = "index_key")
    private Long id;

    @Column(unique = true, length = 50)
    private String memberId;

    @Column(length = 500)
    private String memberPassword;

    @Column(length = 30)
    private String userName;

    @Column(name = "member_role")
    private String role;

    private boolean enabled;

    private LocalDateTime createdAt;

    @Builder
    public Member(String memberId, String memberPassword, String userName, String role){
        this.memberId = memberId;
        this.memberPassword = memberPassword;
        this.userName = userName;
        this.createdAt = LocalDateTime.now();
        this.role = "CERTIFIED_USER";
    }

}
