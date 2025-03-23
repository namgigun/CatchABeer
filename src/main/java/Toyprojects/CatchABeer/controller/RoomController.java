package Toyprojects.CatchABeer.controller;

import Toyprojects.CatchABeer.dto.EventDto;
import Toyprojects.CatchABeer.entity.Event;
import Toyprojects.CatchABeer.entity.Member;
import Toyprojects.CatchABeer.entity.Room;
import Toyprojects.CatchABeer.service.EventService;
import Toyprojects.CatchABeer.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
@Controller
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

    private final EventService eventService;
    // 방 입장 (멤버 정보와 이벤트 정보를 조회)
    @GetMapping("room/{roomId}")
    public String roomInfo(@PathVariable("roomId") Long roomId, Model model) {
        Room room = roomService.findRoom(roomId);
        List<Event> events = eventService.findByRoom(room);
        model.addAttribute("members", room.getMembers());
        model.addAttribute("events", events);
        return "room";
    }

    // 일정 추가
    @PostMapping("room/{roomId}/addEvent")
    public String addEvent(@RequestBody EventDto eventDto, @PathVariable Long roomId) {
        Room room = roomService.findRoom(roomId);
        eventDto.setRoom(room);
        Event event = new Event(eventDto);

        // DB에 event 정보를 저장한다.
        eventService.save(event);
        return "room";
    }
}
