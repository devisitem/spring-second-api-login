package me.kimchidev.springsecondapilogin.repository;

import lombok.RequiredArgsConstructor;
import me.kimchidev.springsecondapilogin.domain.AuthenticationRequest;
import me.kimchidev.springsecondapilogin.domain.Member;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberRepository {

    @PersistenceContext
    private final EntityManager em;

    @Transactional
    public void save(Member member){

        em.persist(member);
    }


    public Member findById(String memberId){
        return em.createQuery("select m from Member m where m.memberId = :memberId", Member.class)
                .setParameter("memberId",memberId)
                .getSingleResult();
    }


}
