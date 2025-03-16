package Toyprojects.CatchABeer.service;

import Toyprojects.CatchABeer.entity.Event;
import Toyprojects.CatchABeer.entity.Member;
import Toyprojects.CatchABeer.entity.Room;
import Toyprojects.CatchABeer.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoomService {
    private final RoomRepository roomRepository;

    public Room findRoom(Long id) {
        Optional<Room> result = roomRepository.findById(id);
        return result.orElseThrow(null);
    }

    @Transactional
    public void save(Room room) {
        roomRepository.save(room);
    }
}
