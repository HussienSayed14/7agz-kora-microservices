package com.owner.OwnerMicroservice.repositories;

import com.owner.OwnerMicroservice.entities.FieldOwner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FieldOwnerRepository extends JpaRepository<FieldOwner, String> {

}
