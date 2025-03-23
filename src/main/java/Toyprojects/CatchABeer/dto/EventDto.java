package Toyprojects.CatchABeer.dto;

import Toyprojects.CatchABeer.entity.Room;
import lombok.Data;

@Data
public class EventDto {
    private String title;
    private String start;
    private String end;
    private Room room;
}
