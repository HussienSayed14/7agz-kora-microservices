package com.owner.OwnerMicroservice.field;


import com.owner.OwnerMicroservice.field.requests.CreateFieldRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FieldService {
    public ResponseEntity createField(CreateFieldRequest request) {

        return null;
    }

    public ResponseEntity getFieldById(String fieldId) {
        return null;
    }

    public ResponseEntity getAllOwnerFields(String bearerToken) {
        return null;

    }
}
