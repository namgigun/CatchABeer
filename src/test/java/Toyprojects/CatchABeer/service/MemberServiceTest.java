package Toyprojects.CatchABeer.service;

import Toyprojects.CatchABeer.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {
    @Autowired MemberService memberService;

    @Test
    public void testJoin() {
        Member member = new Member("member1", "1234");
        memberService.join(member);
    }
}