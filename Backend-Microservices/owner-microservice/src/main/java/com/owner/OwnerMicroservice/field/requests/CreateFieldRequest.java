package com.owner.OwnerMicroservice.field.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateFieldRequest {

    private String fieldName;
    private String fieldType;
    private String fieldSize;
    private String fieldLocation;
    private int hourPrice;
    private int rating;
}
