package com.owner.OwnerMicroservice.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "FIELD")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Field {
    @Id
    private long uid;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long counter;
    private String fieldName;
    private String fieldType;
    private String fieldSize;
    private String fieldLocation;
    private boolean isActive;
    private boolean isAvailable;
    private boolean isDeleted;
    private boolean isBlocked;
    private long hoursBooked;
    private int rating;
    private int creationDate;

}
