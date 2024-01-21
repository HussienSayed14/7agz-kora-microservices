package com.owner.OwnerMicroservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "FIELD_OWNER")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FieldOwner {

    @Id
    private String username;

}
