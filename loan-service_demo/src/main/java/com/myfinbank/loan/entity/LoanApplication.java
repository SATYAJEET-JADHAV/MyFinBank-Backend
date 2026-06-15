package com.myfinbank.loan.entity;

import com.myfinbank.loan.enums.LoanStatus;
import jakarta, precision = 12, scale = 2)import jakarta.persistence.*;
    private BigDecimal loanAmount;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal interestRate;

    @Column(nullable = false)
    private Integer tenureMonths;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LoanStatus status;

    @Column(length = 500)
    private String remarks;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();

        if (this.status == null) {
            this.status = LoanStatus.SUBMITTED;
        }
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "loan_applications")
public class LoanApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
     * Customer ID comes from customer-service.
     * Do not duplicate customer details in loan-service.
     */
    @Column(nullable = false)
    private Long customerId;

    @Column(nullable = false)
    private Long vehicleId;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal vehiclePrice;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal downPayment;

