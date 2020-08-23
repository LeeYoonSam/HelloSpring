package com.ys.hellospring.controller;

import com.ys.hellospring.service.MemberService;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    /**
     * Service 등을 사용할때 사용할 객체를 new 로 생성하는 대신에 스프링 컨테이너에 등록해서 사용
     */
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
