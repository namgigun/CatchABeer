package Toyprojects.CatchABeer.service;

import Toyprojects.CatchABeer.dto.EventDto;
import Toyprojects.CatchABeer.entity.Event;
import Toyprojects.CatchABeer.entity.Member;
import Toyprojects.CatchABeer.entity.Room;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
class EventServiceTest {
    @Autowired EventService eventService;
    @Autowired RoomService roomService;
    @Autowired MemberService memberService;
    // Event, Room DB에 올바르게 생성되고 연관관계도 맞게 생성되는지 테스트

    @Test
    @Transactional
    @Rollback(value = false)
    public void testSave() {
        Member member1 = new Member("member1", "1234");
        memberService.join(member1);

        Member findMember = memberService.findByUsername("member1");

        EventDto eventDto = new EventDto();
        eventDto.setTitle("title");
        eventDto.setStart("sDate");
        eventDto.setEnd("eDate");
        eventDto.setMember(findMember);
        eventDto.setRoom(findMember.getRoom());

        Event event = new Event(eventDto);
        eventService.save(event);
    }
}