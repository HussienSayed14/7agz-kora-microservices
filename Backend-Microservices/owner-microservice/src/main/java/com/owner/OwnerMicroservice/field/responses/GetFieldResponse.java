package com.owner.OwnerMicroservice.field.responses;

import com.owner.OwnerMicroservice.entities.Field;
import com.owner.OwnerMicroservice.util.GenericResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetFieldResponse extends GenericResponse {
    private Field field;
}
