package Toyprojects.CatchABeer.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String password;

    @ManyToOne
    @JoinColumn(name = "ROOM_ID")
    private Room room;

    public Member(String name, String password, Room room) {
        this.name = name;
        this.password = password;
        this.room = room;
    }

    public Member(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
