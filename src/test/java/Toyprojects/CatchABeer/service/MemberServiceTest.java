package Toyprojects.CatchABeer.service;

import Toyprojects.CatchABeer.entity.Member;
import Toyprojects.CatchABeer.entity.Room;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {
    @Autowired MemberService memberService;
    @Autowired RoomService roomService;
    // 회원가입 테스트
    @Test
    public void testJoin() {
        Member member = new Member("member1", "1234");
        memberService.join(member);
    }

    @Test
    public void changeRoom() {
        Room room1 = new Room();
        Room room2 = new Room();
        roomService.save(room1);
        roomService.save(room2);

        Member member1 = new Member("member1", "1234");
        memberService.join(member1);

        memberService.updateRoom(member1.getUsername(), room2.getId());
    }
}