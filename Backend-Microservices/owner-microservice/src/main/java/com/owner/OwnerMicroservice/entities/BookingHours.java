package com.owner.OwnerMicroservice.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "BOOKING_HOURS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BookingHours {
        @Id
        private long uid;
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long counter;
        private int bookingDate;
        private int price;
        private int bookingFromTime;
        private int bookingToTime;
        private boolean hasOffer;
        private int offerPercentage;
        private int offerPrice;
        private boolean isBooked;
        private String bookingStatus; // Available, Booked, Locked
        private String hourStatus; // Available, Pending, Unavailable
        private int fee;
        @ManyToOne
        @JoinColumn(name="FIELD_ID", nullable=false)
        private Field fieldId;

}
