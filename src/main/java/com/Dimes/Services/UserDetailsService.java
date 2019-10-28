package com.Dimes.Services;

import com.Dimes.Models.Lender;
import com.Dimes.Repositories.AuthRepository;
import com.Dimes.security.MyUserPrincpal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {


    private AuthRepository authRepository;

    @Autowired
    public UserDetailsService(AuthRepository repository){this.authRepository = repository;}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Lender lender  = authRepository.findByUsername(username);

        if (lender == null)
            throw  new UsernameNotFoundException("User not found with name "+username);

        return new MyUserPrincpal(lender);
    }
}
