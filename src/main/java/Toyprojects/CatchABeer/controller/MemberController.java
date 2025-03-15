package Toyprojects.CatchABeer.controller;

import Toyprojects.CatchABeer.MemberDto;
import Toyprojects.CatchABeer.entity.Member;
import Toyprojects.CatchABeer.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;

    // 회원가입
    @GetMapping("/")
    public String signupGet() {
        return "signup";
    }
    @PostMapping("/new")
    public String signupPost(@ModelAttribute MemberDto memberDto) {
        Member member = new Member(memberDto.getId(), memberDto.getPassword());
        Long memberId = memberService.join(member);
        Member findMember = memberService.findOne(memberId);

        return "redirect:/room/" + findMember.getRoom().getId();
    }
}
