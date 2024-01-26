package com.owner.OwnerMicroservice.repositories;

import com.owner.OwnerMicroservice.entities.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FieldRepository extends JpaRepository<Field, String >{

    @Query(value = "SELECT f FROM Field f WHERE f.uid = :fieldId")
    Field getFieldById(String fieldId);
}
