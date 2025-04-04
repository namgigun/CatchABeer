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
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    private String username;

    private String password;

    @ManyToOne
    @JoinColumn(name = "ROOM_ID")
    private Room room;

    @OneToMany(mappedBy = "member")
    @JsonIgnore
    private List<Event> events = new ArrayList<>();

    public Member(String username, String password, Room room) {
        this.username = username;
        this.password = password;
        this.room = room;
    }

    public Member(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void changeRoom(Room room) {
        // 기존 방에서 현재멤버 삭제
        this.room.offMember(this);
        // 기존 방을 새로운 방으로 바꾼 후 새방에 회원을 추가
        this.room = room;
        room.addMember(this);
    }

    public void addEvent(Event event) {
        events.add(event);
    }
}
