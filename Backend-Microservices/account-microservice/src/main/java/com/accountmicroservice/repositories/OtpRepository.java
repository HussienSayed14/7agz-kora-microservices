package com.accountmicroservice.repositories;

import com.accountmicroservice.entities.OTP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OtpRepository extends JpaRepository<OTP, Long>{

    @Query(value = "SELECT r from OTP r WHERE r.email = :email AND r.otpType = 'Register'")
    OTP getRegisterationOtpByEmail(String email);
}
