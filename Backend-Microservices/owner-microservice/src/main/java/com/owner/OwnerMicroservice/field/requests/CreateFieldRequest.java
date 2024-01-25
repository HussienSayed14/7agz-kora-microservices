package com.owner.OwnerMicroservice.field.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateFieldRequest {

    @NotNull(message = "Field name is mandatory")
    private String fieldName;
    @NotNull(message = "Field type is mandatory")
    private String fieldType;
    @NotNull(message = "Field size is mandatory")
    private String fieldSize;
    @NotNull(message = "Field location is mandatory")
    private String fieldLocation;
    private int hourPrice;
}
