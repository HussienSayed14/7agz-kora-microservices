package com.fieldmicroservice.fieldmicroservice.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "BOOKING_HOUR")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BookingHour {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "BOOKING_DATE")
    private int bookingDate;
    @Column(name = "BOOKING_FROM_TIME")
    private int bookingFromTime;
    @Column(name = "BOOKING_TO_TIME")
    private int bookingToTime;
    @Column(name = "HAS_OFFER")
    private boolean hasOffer;
    @Column(name = "OFFER_PERCENTAGE")
    private int offerPercentage;
    @Column(name = "OFFER_PRICE")
    private double offerPrice;
    @Column(name = "IS_BOOKED")
    private boolean isBooked;
    @Column(name = "BOOKING_PRICE")
    private double bookingPrice;
    @Column(name = "BOOKING_STATUS")
    private String bookingStatus;
    @Column(name = "BOOKING_TIME")
    private int bookingTime;
    @Column(name = "IS_LOCKED")
    private boolean isLocked;
    @Column(name = "IS_AVAILABLE")
    private boolean isAvailable;
    @ManyToOne
    @JoinColumn(name="FIELD_ID", nullable=false)
    private Field field;
    @ManyToOne
    @JoinColumn(name="USER_ID", nullable=false)
    private User bookingUser;




}
