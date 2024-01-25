package com.owner.OwnerMicroservice.field;

import com.owner.OwnerMicroservice.field.requests.CreateFieldRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/owner/api/v1/field")
@RequiredArgsConstructor
public class FieldController {
    private final FieldService fieldService;


    @PostMapping("/createField")
    public ResponseEntity createField(@RequestBody CreateFieldRequest request){
        return fieldService.createField(request);
    }

    @GetMapping("/getField/{fieldId}")
    public ResponseEntity getFieldById(@PathVariable String fieldId){
        return fieldService.getFieldById(fieldId);
    }

    @GetMapping("/getAllFields")
    public ResponseEntity getAllOwnerFields(@RequestHeader(value = "Authorization") String bearerToken){
        return fieldService.getAllOwnerFields(bearerToken);
    }






}
