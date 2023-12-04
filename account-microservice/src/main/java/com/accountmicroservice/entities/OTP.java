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
        @Column(name = "ID")
        private long id;
        @Column(name = "EMAIL")
        private String email;
        @Column(name = "PHONE_NUMBER")
        private String phoneNumber;
        @Column(name = "OTP_TYPE")
        private String otpType;
        @Column(name = "VERIFICATION_TYPE")
        private String verificationType;
        @Column(name = "OTP")
        private String otp;
        @Column(name = "CREATION_DATE")
        private String creationDate;
        @Column(name = "CREATION_TIME")
        private String creationTime;
        @Column(name = "EXPIRY_DATE")
        private String expiryDate;
        @Column(name = "EXPIRY_TIME")
        private String expiryTime;


}
