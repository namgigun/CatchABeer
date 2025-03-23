package Toyprojects.CatchABeer.repository;

import Toyprojects.CatchABeer.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    @Query("select e from Event e where e.room.id = :roomId")
    List<Event> findByRoomId(@Param("roomId") Long roomId);
}
