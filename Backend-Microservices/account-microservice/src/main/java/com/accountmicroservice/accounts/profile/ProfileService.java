package com.accountmicroservice.accounts.profile;


import com.accountmicroservice.accounts.profile.requests.ChangePasswordRequest;
import com.accountmicroservice.config.JwtService;
import com.accountmicroservice.entities.User;
import com.accountmicroservice.repositories.UserRepository;
import com.accountmicroservice.util.GenericResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    public ResponseEntity<GenericResponses> changePassword(ChangePasswordRequest request, String bearerToken) {
        GenericResponses response = new GenericResponses();
        String token = bearerToken.substring(7);
        String email = jwtService.extractUserEmail(token);
        User user = userRepository.findByEmail(email);

        if (passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(request.getNewPassword()));
            userRepository.save(user);
            response.setSuccessful();
        } else {
            response.setWrongOldPassword();
        }
        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }
}
