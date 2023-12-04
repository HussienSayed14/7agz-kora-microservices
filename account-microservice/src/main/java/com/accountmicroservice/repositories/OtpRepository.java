package com.accountmicroservice.repositories;

import com.accountmicroservice.entities.OTP;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OtpRepository extends JpaRepository<OTP, Long>{

}
