package Toyprojects.CatchABeer.service;

import Toyprojects.CatchABeer.entity.Member;
import Toyprojects.CatchABeer.entity.Room;
import Toyprojects.CatchABeer.repository.MemberRepository;
import Toyprojects.CatchABeer.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    private final RoomRepository roomRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // 회원가입
    @Transactional
    public Long join(Member member) {
        // 방 정보 생성
        Room room = new Room();

        // 멤버 정보 생성
        String encPassword = bCryptPasswordEncoder.encode(member.getPassword());
        Member joinMember = new Member(member.getUsername(), encPassword, room);
        room.addMember(joinMember);

        roomRepository.save(room);
        memberRepository.save(joinMember);

        return joinMember.getId();
    }

    // 전체 회원 조회
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    // 회원 식별자 조회
    public Member findOne(Long id) {
        Optional<Member> result = memberRepository.findById(id);
        return result.orElseThrow(null);
    }

    // 회원 아이디 조회
    public Member findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    // 현재 로그인 한 회원의 방 정보 수정
    @Transactional
    public void updateRoom(String username, Long roomId) {
        Member member = memberRepository.findByUsername(username);
        Room room = roomRepository.findById(roomId).orElseThrow();

        member.changeRoom(room);
    }
}
