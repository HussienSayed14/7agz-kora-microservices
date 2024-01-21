package com.owner.OwnerMicroservice.repositories;

import com.owner.OwnerMicroservice.entities.Field;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FieldRepository extends JpaRepository<Field, String >{
}
