package Toyprojects.CatchABeer.controller;

import Toyprojects.CatchABeer.auth.AccountDetails;
import Toyprojects.CatchABeer.dto.EventDto;
import Toyprojects.CatchABeer.entity.Event;
import Toyprojects.CatchABeer.entity.Member;
import Toyprojects.CatchABeer.entity.Room;
import Toyprojects.CatchABeer.service.EventService;
import Toyprojects.CatchABeer.service.MemberService;
import Toyprojects.CatchABeer.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//@RestController
@Controller
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

    private final EventService eventService;

    private final MemberService memberService;

    // 로그인한 사용자의 방으로 이동
    @GetMapping("/moveRoom")
    public String moveRoom() {
        // 현재 로그인 한 유저정보를 가져온다.
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AccountDetails accountDetails = (AccountDetails)principal;
        String username = accountDetails.getUsername();
        Member loginMember = memberService.findByUsername(username);

        return "redirect:/room/" + loginMember.getRoom().getId();
    }

    // 방 입장 (멤버 정보와 이벤트 정보를 조회)
    @GetMapping("room/{roomId}")
    public String roomInfo(@PathVariable("roomId") Long roomId, Model model) {
        // 현재 로그인 한 유저정보를 가져온다.
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AccountDetails accountDetails = (AccountDetails)principal;
        String username = accountDetails.getUsername();
        Member loginMember = memberService.findByUsername(username);

        Room room = roomService.findRoom(roomId);

        // 현재 room 안에 있는 member로 부터의 모든 데이터를 events 안에 넣어야함.
        List<Event> events = new ArrayList<>();

        model.addAttribute("members", room.getMembers());
        model.addAttribute("events", events);
        return "room";
    }

    // 일정 추가
    @PostMapping("room/{roomId}/addEvent")
    public String addEvent(@RequestBody EventDto eventDto, @PathVariable Long roomId) {
        // 현재 로그인한 회원 정보 Event에 저장
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AccountDetails accountDetails = (AccountDetails)principal;
        String username = accountDetails.getUsername();
        Member loginMember = memberService.findByUsername(username);
        eventDto.setMember(loginMember);

        Event event = new Event(eventDto);

        // DB에 event 정보를 저장한다.
        eventService.save(event);
        return "room";
    }

    // 방 초대
    @GetMapping("invite/{roomId}")
    public String inviteRoom(@PathVariable Long roomId) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AccountDetails accountDetails = (AccountDetails)principal;
        String username = accountDetails.getUsername();
        Member loginMember = memberService.findByUsername(username);

        // 로그인 멤버의 방을 초대된 방으로 바꾼다.
        memberService.updateRoom(loginMember.getUsername(), roomId);

        return "redirect:/moveRoom";
    }
}
