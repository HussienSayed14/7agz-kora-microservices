package com.owner.OwnerMicroservice.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "FIELD_FEES_NARRATION")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FieldFees {
    @Id
    private String uid;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long counter;
    private int creationDate;
    private int creationTime;
    private int priceWithoutFee;
    private int fee;
    private int debits; // Previous debits + fee;
    private boolean isCredit;
    @ManyToOne
    @JoinColumn(name="FIELD_ID", nullable=false)
    private Field fieldId;
    @ManyToOne
    @JoinColumn(name="OWNER_ID", nullable=false)
    private FieldOwner ownerId;









}
