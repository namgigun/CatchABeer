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

    private String content;
    private String startDate;
    private String endDate;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "ROOM_ID")
    private Room room;

    public Event(EventDto eventDto) {
        this.content = eventDto.getTitle();
        this.startDate = eventDto.getStart();
        this.endDate = eventDto.getEnd();
        this.room = eventDto.getRoom();
        this.member = eventDto.getMember();

        room.addEvent(this);
        member.addEvent(this);
    }
}
