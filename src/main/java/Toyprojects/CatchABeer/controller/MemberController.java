package Toyprojects.CatchABeer.controller;

import Toyprojects.CatchABeer.dto.MemberDto;
import Toyprojects.CatchABeer.entity.Member;
import Toyprojects.CatchABeer.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

//@RestController
@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;
    /** 회원가입 **/
    // 회원가입 페이지 불러오기
    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    // 회원 정보를 생성
    @PostMapping("/new")
    public String Register(@ModelAttribute MemberDto memberDto) {
        Member member = new Member(memberDto.getUsername(), memberDto.getPassword());
        memberService.join(member);

        return "redirect:/";
    }

    // 로그인
    @GetMapping("/")
    public String login() {
        return "loginForm";
    }

}
