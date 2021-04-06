package com.example.demo.security;

import com.example.demo.domain.User;
import com.example.demo.domain.UserRole;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
@Log4j2
public class UserDetailsImpl implements UserDetails {
    private final User user;
    public UserDetailsImpl(User user) {
        this.user = user;
    }
    public User getUser() {
        return user;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }
    public String getRawPass() {return user.getRawPass();};
    @Override
    public String getUsername() {
        return user.getUsername();
    }
    public String getName() { return user.getName();}
    public String getEmail() {return user.getEmail();};
    public String getPhone() {return user.getPhone();};
    public UserRole getRole() {return user.getRole();};
    public Long getId() { return user.getId();}

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
    private static final String ROLE_PREFIX = "ROLE_";
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //유저 객체에 유저 역할을 빼오기
        UserRole userRole = user.getRole();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(ROLE_PREFIX + userRole.toString());
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(authority);
        return authorities;
    }
}