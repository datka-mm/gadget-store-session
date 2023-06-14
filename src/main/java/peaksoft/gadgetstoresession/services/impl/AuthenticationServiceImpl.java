package peaksoft.gadgetstoresession.services.impl;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.gadgetstoresession.config.JwtService;
import peaksoft.gadgetstoresession.dto.request.SignInRequest;
import peaksoft.gadgetstoresession.dto.request.SignUpRequest;
import peaksoft.gadgetstoresession.dto.response.AuthenticationResponse;
import peaksoft.gadgetstoresession.enums.Role;
import peaksoft.gadgetstoresession.models.User;
import peaksoft.gadgetstoresession.repositories.UserRepository;
import peaksoft.gadgetstoresession.services.AuthenticationService;

import java.time.ZonedDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    @Override
    public AuthenticationResponse signUp(SignUpRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EntityExistsException("Email already exists");
        }

        User user = User
                .builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .createdDate(ZonedDateTime.now())
                .updatedDate(ZonedDateTime.now())
                .build();

        userRepository.save(user);

        return AuthenticationResponse
                .builder()
                .token(jwtService.generateToken(user))
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    @Override
    public AuthenticationResponse signIn(SignInRequest request) {
        User user = userRepository.getUserByEmail(request.getEmail()).orElseThrow(
                () -> new EntityNotFoundException("User with email: " + request.getEmail() + " not found!")
        );

        if (request.getPassword().isBlank()) {
            throw new BadCredentialsException("Password is blank");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Wrong password!");
        }

        return AuthenticationResponse
                .builder()
                .token(jwtService.generateToken(user))
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    @PostConstruct
    public void initAdmin() {
        User user = User.builder()
                .firstName("Admin")
                .lastName("Super")
                .email("admin@gmail.com")
                .password(passwordEncoder.encode("admin"))
                .role(Role.ADMIN)
                .createdDate(ZonedDateTime.now())
                .updatedDate(ZonedDateTime.now())
                .build();
        userRepository.save(user);
    }
}
