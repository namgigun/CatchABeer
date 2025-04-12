package Toyprojects.CatchABeer.repository;

import Toyprojects.CatchABeer.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    public Member findByUsername(String username);
}