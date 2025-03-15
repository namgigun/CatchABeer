package Toyprojects.CatchABeer.controller;

import Toyprojects.CatchABeer.entity.Member;
import Toyprojects.CatchABeer.entity.Room;
import Toyprojects.CatchABeer.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@RestController
@Controller
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;
    @GetMapping("room/{roomId}")
    public String roomInfo(@PathVariable("roomId") Long roomId, Model model) {
        Room room = roomService.findRoom(roomId);
        model.addAttribute("members", room.getMembers());
        return "room";
    }


}
