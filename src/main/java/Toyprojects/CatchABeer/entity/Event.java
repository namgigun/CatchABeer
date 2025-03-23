package Toyprojects.CatchABeer.entity;

import Toyprojects.CatchABeer.dto.EventDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@ToString
@Getter
@NoArgsConstructor
public class Event {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ROOM_ID")
    private Room room;

    private String content;
    private String startDate;
    private String endDate;

    public Event(EventDto eventDto) {
        this.room = eventDto.getRoom();
        this.content = eventDto.getTitle();
        this.startDate = eventDto.getStart();
        this.endDate = eventDto.getEnd();
        room.addEvent(this);
    }

    public Event(Room room) {
        this.room = room;
        this.room.addEvent(this);
    }
}
