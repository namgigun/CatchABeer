package Toyprojects.CatchABeer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Room {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROOM_ID")
    private Long id;

    @JsonIgnore
    @OneToMany(mappedBy = "room")
    private List<Member> members = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "room")
    private List<Event> events = new ArrayList<>();

    public void addMember(Member member) {
        members.add(member);
    }

    public void offMember(Member member) {
        members.remove(member);
    }

    public void addEvent(Event event) {
        events.add(event);
    }
}
