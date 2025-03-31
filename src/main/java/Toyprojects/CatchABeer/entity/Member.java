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

    private String username;

    private String password;

    @ManyToOne
    @JoinColumn(name = "ROOM_ID")
    private Room room;

    public Member(String username, String password, Room room) {
        this.username = username;
        this.password = password;
        this.room = room;
    }

    public Member(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
