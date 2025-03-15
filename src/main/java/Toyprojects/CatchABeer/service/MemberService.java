package Toyprojects.CatchABeer.service;

import Toyprojects.CatchABeer.entity.Member;
import Toyprojects.CatchABeer.entity.Room;
import Toyprojects.CatchABeer.repository.MemberRepository;
import Toyprojects.CatchABeer.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
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

    // 회원가입
    @Transactional
    public Long join(Member member) {
        Room room = new Room("Room" + randomStr());
        Member joinMember = new Member(member.getName(), member.getPassword(), room);
        room.addMember(joinMember);

        roomRepository.save(room);
        memberRepository.save(joinMember);

        return joinMember.getId();
    }

    // 무작위 문자열 만들기
    private String randomStr() {
        return RandomStringUtils.random(10);
    }

    // 회원조회

    // 전체 회원 조회
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    // 회원 한명 조회
    public Member findOne(Long id) {
        Optional<Member> result = memberRepository.findById(id);
        return result.orElseThrow(null);
    }
}
