package com.Dimes.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.Dimes.Models.Lender;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class MyUserPrincpal implements UserDetails {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Lender lender;

    public MyUserPrincpal(Lender lender){
        this.lender = lender;}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+lender.getRole());
        authorities.add(authority);

        return authorities;
    }

    @Override
    public String getPassword() {
        return lender.getPassword();
    }

    @Override
    public String getUsername() {
        return lender.getUsername();
    }

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
}
