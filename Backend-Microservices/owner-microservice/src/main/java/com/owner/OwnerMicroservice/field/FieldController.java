package com.owner.OwnerMicroservice.field;

import com.owner.OwnerMicroservice.field.requests.CreateFieldRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/owner/api/v1/field")
@RequiredArgsConstructor
public class FieldController {
    private final FieldService fieldService;


    @PostMapping("/createField")
    public ResponseEntity createField(@RequestBody CreateFieldRequest request){
        return fieldService.createField(request);
    }





}
