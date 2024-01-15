package com.fieldmicroservice.fieldmicroservice.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Field")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Field {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "FIELD_NAME")
    private String fieldName;
    @Column(name = "FIELD_TYPE")
    private String fieldType;
    @Column(name = "FIELD_SIZE")
    private String fieldSize;
    @Column(name = "FIELD_LOCATION")
    private String fieldLocation;
    @Column(name= "IS_ACTIVE")
    private boolean isActive;
    @Column(name = "CREATION_DATE")
    private int creationDate;
    @Column(name = "CREATION_TIME")
    private int creationTime;
    @Column(name = "IS_AVAILABLE")
    private boolean isAvailable;
    @Column(name = "RATING")
    private int rating;
    @Column(name= "IS_DELETED")
    private boolean isDeleted;
    @Column(name = "DELETION_DATE")
    private int deletionDate;
    @Column(name = "DELETION_TIME")
    private int deletionTime;
    @Column(name = "DELETION_REASON")
    private String deletionReason;
    @Column(name = "HOURS_BOOKED")
    private long hoursBooked;
    @Column(name = "PRIORITY")
    private int priority;
    @Column(name = "IS_BLOCKED")
    private boolean isBlocked;
    @Column(name= "AVERAGE_PRICE")
    private double averagePrice;
    @OneToMany(mappedBy = "field")
    private List<BookingHour> bookingHoursList;
    @ManyToOne
    @JoinColumn(name="USER_ID", nullable=false)
    private User fieldOwner;

}
