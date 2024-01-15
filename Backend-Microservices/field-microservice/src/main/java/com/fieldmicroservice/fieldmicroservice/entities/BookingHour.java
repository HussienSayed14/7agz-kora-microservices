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


}
