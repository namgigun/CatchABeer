package Toyprojects.CatchABeer.repository;

import Toyprojects.CatchABeer.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
