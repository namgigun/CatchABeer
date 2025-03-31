package Toyprojects.CatchABeer.auth;

import Toyprojects.CatchABeer.entity.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class AccountDetails implements UserDetails {
    private Member member;

    public AccountDetails(Member member) {
        this.member = member;
    }

    // 유저의 권한을 return 한다.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getUsername();
    }
}
