package com.tdg.mur.service;

import com.tdg.mur.model.User;
import com.tdg.mur.repos.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import java.util.Collection;
import java.util.Optional;
 
import static java.util.Collections.singletonList;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserRepository userRepo;
	
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        Optional<User> foundUser = userRepo.findByUsername(username);
        User user = foundUser.orElseThrow(() -> new UsernameNotFoundException("No user " +
                        "Found with username : " + username));
 
        return new org.springframework.security.core.
        		userdetails.User(user.getUsername(),user.getPassword(),
                user.isEnabled(), true, true,true, getAuthorities("USER"));
    }
	
	private Collection<? extends GrantedAuthority> getAuthorities(String role) {
		return singletonList(new SimpleGrantedAuthority(role));
	}
}
