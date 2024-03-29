package com.owner.OwnerMicroservice.repositories;

import com.owner.OwnerMicroservice.entities.FieldOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface FieldOwnerRepository extends JpaRepository<FieldOwner, String> {


    @Query(value = "SELECT o FROM FieldOwner o WHERE o.userName =:userName")
    FieldOwner findOwnerByUsername(String userName);

    @Query(value = "SELECT o FROM FieldOwner o WHERE o.email =:email")
    Optional<FieldOwner> findByEmailOptional(String email);

}
