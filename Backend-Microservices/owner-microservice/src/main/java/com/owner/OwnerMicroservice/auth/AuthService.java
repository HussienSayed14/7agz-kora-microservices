package com.owner.OwnerMicroservice.auth;

import com.owner.OwnerMicroservice.auth.requests.CreateOwnerRequest;
import com.owner.OwnerMicroservice.entities.FieldOwner;
import com.owner.OwnerMicroservice.repositories.FieldOwnerRepository;
import com.owner.OwnerMicroservice.util.DateTimeFormatter;
import com.owner.OwnerMicroservice.util.GenericResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final FieldOwnerRepository fieldOwnerRepository;
    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);
    public ResponseEntity<GenericResponse> register(CreateOwnerRequest createOwnerRequest) {
        GenericResponse response = new GenericResponse();
        if(!isUserRegistered(createOwnerRequest.getUserName(),response) && createOwner(createOwnerRequest,response)){
            response.setSuccessful();
        }
        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }

    private boolean isUserRegistered(String userName,GenericResponse response) {
        FieldOwner owner = fieldOwnerRepository.findOwnerByUsername(userName);
        if (owner == null) {
            return false;
        }
        response.setUserAlreadyExist();
        return true;
    }

    private boolean createOwner(CreateOwnerRequest createOwnerRequest,GenericResponse response) {
        FieldOwner owner = FieldOwner.builder()
                .userName(createOwnerRequest.getUserName())
                .email(createOwnerRequest.getEmail())
                .password(createOwnerRequest.getPassword()) //TODO: hash password
                .fullName(createOwnerRequest.getFullName())
                .phoneNumber(createOwnerRequest.getPhoneNumber())
                .address(createOwnerRequest.getAddress())
                .nationalID(createOwnerRequest.getNationalID())
                .role("OWNER")
                .isActive(true)
                .isBlocked(false)
                .isLocked(false)
                .isVerified(false)
                .creationDate(DateTimeFormatter.getCurrentDate())
                .build();
        try {
            fieldOwnerRepository.save(owner);
            return true;
        } catch (Exception e) {
            logger.error("Error while creating owner: " + e.getMessage());
            response.setServerErrorHappened();
            return false;
        }

    }
}
