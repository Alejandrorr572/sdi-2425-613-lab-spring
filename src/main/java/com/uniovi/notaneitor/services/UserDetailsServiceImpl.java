package com.uniovi.notaneitor.services;

import org.springframework.stereotype.Service;
import com.uniovi.notaneitor.entities.User;
import com.uniovi.notaneitor.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Set;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UsersRepository usersRepository;
    public UserDetailsServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String dni) throws UsernameNotFoundException {
        User user = usersRepository.findByDni(dni);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_STUDENT"));
        if (user == null) {
            throw new UsernameNotFoundException(dni);
        }
        return new org.springframework.security.core.userdetails.User(
                user.getDni(), user.getPassword(), grantedAuthorities);
    }
}
