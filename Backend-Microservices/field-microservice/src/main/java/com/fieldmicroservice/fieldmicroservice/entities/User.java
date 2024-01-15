package com.fieldmicroservice.fieldmicroservice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Table(name = "USER")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User {

    @Id
    @Column(name="EMAIL", unique = true)
    private String email;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;
    @Column(name = "ROLE")
    private Roles role;
    @Column(name = "DATE_OF_BIRTH")
    private int dateOfBirth;
    @Column(name = "SECURITY_QUESTION")
    private String securityQuestion;
    @Column(name = "SECURITY_ANSWER")
    private String securityAnswer;
    @Column(name = "IS_BLOCKED")
    private boolean isBlocked;
    @Column(name = "IS_VERIFIED")
    private boolean isVerified;
    @Column(name = "IS_ACTIVE")
    private boolean isActive;
    @Column(name = "IS_LOCKED")
    private boolean isLocked;
    @Column(name = "NATIONAL_ID")
    private String nationalId;
    @Column(name = "CREATION_DATE")
    private int creationDate;
    @Column(name = "CREATION_TIME")
    private int creationTime;
    @Column(name = "FAILED_LOGIN_ATTEMPTS")
    private int failedLoginAttempts;
    @Column(name = "LAST_LOGIN_DATE")
    private int lastLoginDate;
    @Column(name = "LAST_LOGIN_TIME")
    private int lastLoginTime;
    @Column(name = "LOCK_REMOVAL_DATE")
    private int lockRemovalDate;
    @Column(name = "LOCK_REMOVAL_TIME")
    private int lockRemovalTime;
    @Column(name = "PROFILE_PICTURE")
    private String profilePicture;
    @OneToMany(mappedBy = "fieldOwner")
    private List<Field> fieldList;


}
