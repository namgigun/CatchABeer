package Toyprojects.CatchABeer.service;

import Toyprojects.CatchABeer.dto.EventDto;
import Toyprojects.CatchABeer.entity.Event;
import Toyprojects.CatchABeer.entity.Room;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class EventServiceTest {
    @Autowired EventService eventService;
    @Autowired RoomService roomService;

    // Event, Room DB에 올바르게 생성되고 연관관계도 맞게 생성되는지 테스트
    @Test
    public void testSave() {
        Room room1 = new Room("room1");
        roomService.save(room1);

        EventDto eventDto = new EventDto();
        eventDto.setCalendar_content("content");
        eventDto.setCalendar_start_date("sDate");
        eventDto.setCalendar_end_date("eDate");
        eventDto.setRoom(room1);

        Event event = new Event(eventDto);
        eventService.save(event);

        Assertions.assertThat(event).isEqualTo(room1.getEvents().get(0));
    }
}