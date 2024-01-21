package com.owner.OwnerMicroservice.entities;

import jakarta.persistence.*;
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
    private String userName;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long counter;
    private String email;
    private String password;
    private String fullName;
    private String phoneNumber;
    private String address;
    private String role;
    private String NationalID;
    private boolean isActive;
    private boolean isBlocked;
    private boolean isLocked;
    private int creationDate;
    private int priority;




}
