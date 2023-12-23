package com.accountmicroservice.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "LOGIN_AUDIT")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LoginAudit {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ID")
        private long id;
        @Column(name = "USER_EMAIL")
        private String userEmail;
        @Column(name = "LOGIN_STATUS")
        private String loginStatus;
        @Column(name = "LOGIN_MESSAGE")
        private String loginMessage;
        @Column(name = "LOGIN_DATE")
        private int loginDate;
        @Column(name = "LOGIN_TIME")
        private int loginTime;
        @Column(name = "IP_ADDRESS")
        private String ipAddress;
        @Column(name = "LOGIN_LOCATION")
        private String loginLocation;
        @Column(name = "LOGIN_DEVICE")
        private String loginDevice;
        @Column(name = "LOGIN_USER_AGENT")
        private String loginUserAgent;
        @Column(name = "LOGIN_COUNTRY")
        private String loginCountry;
        @Column(name = "LOGIN_CITY")
        private String loginCity;

}
