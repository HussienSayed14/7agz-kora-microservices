package com.owner.OwnerMicroservice.repositories;

import com.owner.OwnerMicroservice.entities.BookingHours;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingHoursRepository extends JpaRepository<BookingHours,String> {
}
