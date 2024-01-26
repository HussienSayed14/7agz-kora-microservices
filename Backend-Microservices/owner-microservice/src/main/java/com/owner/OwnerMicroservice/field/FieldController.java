package com.owner.OwnerMicroservice.field;

import com.owner.OwnerMicroservice.field.requests.CreateFieldRequest;
import com.owner.OwnerMicroservice.field.responses.GetFieldResponse;
import com.owner.OwnerMicroservice.util.GenericResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/owner/api/v1/field")
@RequiredArgsConstructor
@Tag(name = "Field", description = "Apis Responsible for Create, Update, Delete and Get Fields")
public class FieldController {
    private final FieldService fieldService;


    @PostMapping("/createField")
    @Operation(summary = "Create Field", description = "This Api is used to create a new field.")
    public ResponseEntity<GenericResponse> createField(@RequestBody CreateFieldRequest request){
        return fieldService.createField(request);
    }

    @GetMapping("/getField/{fieldId}")
    public ResponseEntity<GetFieldResponse> getFieldById(@PathVariable String fieldId){
        return fieldService.getFieldById(fieldId);
    }

    @GetMapping("/getAllFields")
    public ResponseEntity getAllOwnerFields(@RequestHeader(value = "Authorization") String bearerToken){
        return fieldService.getAllOwnerFields(bearerToken);
    }






}
