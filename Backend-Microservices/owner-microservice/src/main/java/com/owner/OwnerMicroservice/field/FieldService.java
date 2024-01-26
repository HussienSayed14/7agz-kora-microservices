package com.owner.OwnerMicroservice.field;


import com.owner.OwnerMicroservice.entities.Field;
import com.owner.OwnerMicroservice.field.requests.CreateFieldRequest;
import com.owner.OwnerMicroservice.field.responses.GetFieldResponse;
import com.owner.OwnerMicroservice.repositories.FieldRepository;
import com.owner.OwnerMicroservice.util.DateTimeFormatter;
import com.owner.OwnerMicroservice.util.GenericResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FieldService {

    private final FieldRepository fieldRepository;
    private static final Logger logger = LoggerFactory.getLogger(FieldService.class);
    public ResponseEntity<GenericResponse> createField(CreateFieldRequest request) {
        GenericResponse response = new GenericResponse();
        Field field = Field.builder()
                .uid(UUID.randomUUID().toString())
                .fieldName(request.getFieldName())
                .fieldSize(request.getFieldSize())
                .fieldType(request.getFieldType())
                .fieldLocation(request.getFieldLocation())
                .hourPrice(request.getHourPrice())
                .isActive(true)
                .isAvailable(false)
                .isApproved(false)
                .isDeleted(false)
                .isBlocked(false)
                .rating(0)
                .hoursBooked(0)
                .creationDate(DateTimeFormatter.getCurrentDate())
                .build();
        try {
            fieldRepository.save(field);
            response.setSuccessful();

        }catch (Exception e){
            response.setServerErrorHappened();
            logger.error("Error while creating field: " + e.getMessage());
        }

        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }

    public ResponseEntity<GetFieldResponse> getFieldById(String fieldId) {
        GetFieldResponse response = new GetFieldResponse();
        try {
            Field field = fieldRepository.getFieldById(fieldId);
            if(field == null){
                response.setFieldNotFound();
                return ResponseEntity.status(response.getHttpStatus()).body(response);
            }
            response.setField(field);
            response.setSuccessful();
    }catch (Exception e){
            response.setServerErrorHappened();
            logger.error("Error while getting field: " + e.getMessage());
        }
        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }



    //Todo: Add query to get all fields by owner id using JOIN
    public ResponseEntity getAllOwnerFields(String bearerToken) {
        String jwt = bearerToken.substring(7);
        return null;

    }
}
