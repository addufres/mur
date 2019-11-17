package com.tdg.mur.service;

import com.tdg.mur.dto.AuthenticationResponse;
import com.tdg.mur.dto.LoginRequest;
import com.tdg.mur.dto.RegisterRequest;
import com.tdg.mur.exception.MurException;
import com.tdg.mur.model.NotificationEmail;
import com.tdg.mur.model.User;
import com.tdg.mur.model.VerificationToken;
import com.tdg.mur.repos.UserRepository;
import com.tdg.mur.repos.VerificationTokenRepository;
import com.tdg.mur.security.JwtProvider;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import java.util.Optional;
import java.util.UUID;
 
import static com.tdg.mur.util.Constants.ACTIVATION_EMAIL;
import static java.time.Instant.now;
 
@Service
@AllArgsConstructor
@Slf4j
public class AuthService {
	
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final MailBuilder mailBuilder;
    private final MailService mailService;
    private final VerificationTokenRepository verificationTokenRepository;

    @Transactional
    public void signup(RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(encodePassword(registerRequest.getPassword()));
        user.setCreated(now());
        user.setEnabled(false);

        userRepository.save(user);
        log.info("User Registered Successfully, Sending Authentication Email");
        String token = generateVerificationToken(user);
        String message = mailBuilder.build("Thank you for signing up to MUR, please click on the below url to activate your account : "
                + ACTIVATION_EMAIL + "/" + token);

        mailService.sendMail(new NotificationEmail("Please Activate your account", user.getEmail(), message));
    }

    private String generateVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        verificationTokenRepository.save(verificationToken);
        return token;
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    public AuthenticationResponse login(LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String authenticationToken = jwtProvider.generateToken(authenticate);
        return new AuthenticationResponse(authenticationToken, loginRequest.getUsername());
    }

    public void verifyAccount(String token) {
        Optional<VerificationToken> foundToken = verificationTokenRepository.findByToken(token);
        fetchUserAndEnable(foundToken.orElseThrow(() -> new MurException("Invalid Token")));
    }

    @Transactional
    private void fetchUserAndEnable(VerificationToken verificationToken) {
        String username = verificationToken.getUser().getUsername();
        User user = userRepository.findByUsername(username)
        		.orElseThrow(() -> new MurException("User Not Found with id - " + username));
        user.setEnabled(true);
        userRepository.save(user);
    }

//  @Transactional(readOnly = true)
//  User getCurrentUser() {
//      org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.
//              getContext().getAuthentication().getPrincipal();
//      return userRepository.findByUsername(principal.getUsername()).orElseThrow(() -> new UsernameNotFoundException("User name not found - " + principal.getUsername()));
//  }

//  public boolean isLoggedIn() {
//      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//      return !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
//  }

}
