package Toyprojects.CatchABeer.service;

import Toyprojects.CatchABeer.entity.Event;
import Toyprojects.CatchABeer.repository.EventRepository;
import Toyprojects.CatchABeer.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    // 일정 정보 저장 (이때, event와 room 객체 사이의 관계가 맞게 들어가는지 확인하기)
    @Transactional
    public void save(Event event) {
        eventRepository.save(event);
    }
}
