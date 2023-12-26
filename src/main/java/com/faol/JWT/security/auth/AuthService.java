package com.faol.JWT.security.auth;

import com.faol.JWT.security.jwt.JwtService;
import com.faol.JWT.security.user.Role;
import com.faol.JWT.security.user.User;
import com.faol.JWT.security.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse login(LoginRequest loginRequest){
        return null;
    }

    //primero configuramos register.login lo dejamos para la parte final.
    public AuthResponse register(RegisterRequest registerRequest){
        User user = User.builder()
                .username(registerRequest.getUsername())
                .firstName(registerRequest.getFirstName())
                .lastname(registerRequest.getLastname())
                .country(registerRequest.getCountry())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }


}
