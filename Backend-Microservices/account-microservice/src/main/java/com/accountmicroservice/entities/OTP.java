package com.accountmicroservice.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "OTP")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OTP {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ID", unique = true, nullable = false)
        private long id;
        @Column(name = "EMAIL")
        private String email;
        @Column(name = "PHONE_NUMBER", nullable = true)
        private String phoneNumber;
        @Column(name = "OTP_TYPE")
        private String otpType;
        @Column(name = "VERIFICATION_TYPE", nullable = true)
        private String verificationType;
        @Column(name = "OTP")
        private String otp;
        @Column(name = "CREATION_DATE")
        private int creationDate;
        @Column(name = "CREATION_TIME")
        private int creationTime;
        @Column(name = "EXPIRY_DATE")
        private int expiryDate;
        @Column(name = "EXPIRY_TIME")
        private int expiryTime;


}
