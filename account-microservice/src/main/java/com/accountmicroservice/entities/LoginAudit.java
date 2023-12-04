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
        @Column(name = "EMAIL")
        private String email;
        @Column(name = "LOGIN_DATE")
        private String loginDate;
        @Column(name = "LOGIN_TIME")
        private String loginTime;
        @Column(name = "LOGIN_STATUS")
        private String loginStatus;
        @Column(name = "LOGIN_MESSAGE")
        private String loginMessage;
        @Column(name = "LOGIN_IP")
        private String loginIp;
        @Column(name = "LOGIN_LOCATION")
        private String loginLocation;
        @Column(name = "LOGIN_DEVICE")
        private String loginDevice;
        @Column(name = "LOGIN_BROWSER")
        private String loginBrowser;
        @Column(name = "LOGIN_OS")
        private String loginOs;
        @Column(name = "LOGIN_COUNTRY")
        private String loginCountry;
        @Column(name = "LOGIN_REGION")
        private String loginRegion;
        @Column(name = "LOGIN_CITY")
        private String loginCity;;
        @Column(name = "LOGIN_TIMEZONE")
        private String loginTimezone;

}
