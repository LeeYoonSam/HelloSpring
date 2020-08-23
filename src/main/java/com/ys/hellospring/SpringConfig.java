package com.ys.hellospring;

import com.ys.hellospring.repository.MemberRepository;
import com.ys.hellospring.repository.MemoryMemberRepository;
import com.ys.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
