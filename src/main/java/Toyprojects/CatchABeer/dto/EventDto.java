package Toyprojects.CatchABeer.dto;

import Toyprojects.CatchABeer.entity.Room;
import lombok.Data;

@Data
public class EventDto {
    private String calendar_content;
    private String calendar_start_date;
    private String calendar_end_date;
    private Room room;
}
