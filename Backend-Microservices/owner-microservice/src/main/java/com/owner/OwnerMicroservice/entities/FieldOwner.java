package com.owner.OwnerMicroservice.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "FIELD_OWNER")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FieldOwner implements UserDetails {

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
    private String nationalID;
    private boolean isActive;
    private boolean isBlocked;
    private boolean isLocked;
    private boolean isVerified;
    private int creationDate;
    private int priority;
    @OneToMany(mappedBy = "fieldOwner")
    private List<Field> ownedFieldsList;
    @OneToMany(mappedBy = "ownerId")
    private List<FieldFees> fieldFeesList;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.toString()));
    }

    @Override
    public String getUsername() {
        return this.userName;
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
