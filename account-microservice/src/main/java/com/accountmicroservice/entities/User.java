package com.accountmicroservice.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


@Entity
@Table(name = "USER")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User implements UserDetails {

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
    private String role;
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
    @Column(name = "CREATATION_DATE")
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
