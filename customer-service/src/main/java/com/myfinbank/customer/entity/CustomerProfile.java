package com.myfinbank.customer.entity;

import com.myfinbank.customer.enums.KycStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "customer_profiles")
public class CustomerProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long userId;

    @Column(nullable = false, length = 60)
    private String firstName;

    @Column(nullable = false, length = 60)
    private String lastName;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Column(length = 20)
    private String gender;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, unique = true, length = 15)
    private String mobileNumber;

    @Column(nullable = false, unique = true, length = 20)
    private String panNumber;

    @Column(nullable = false, unique = true, length = 20)
    private String aadhaarNumber;

    private String occupation;

    private String employerName;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal monthlyIncome;

    @Column(nullable = false, length = 500)
    private String address;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false, length = 10)
    private String pincode;

    @Column(nullable = false)
    private Integer creditScore;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private KycStatus kycStatus;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();

        if (this.kycStatus == null) {
            this.kycStatus = KycStatus.PENDING;
        }
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}