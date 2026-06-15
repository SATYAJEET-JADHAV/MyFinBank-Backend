package com.myfinbank.loan.entity;

import com.myfinbank.loan.enums.DocumentType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "loan_documents")
public class LoanDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long loanId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DocumentType documentType;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false, length = 500)
    private String filePath;

    private LocalDateTime uploadDate;

    @PrePersist
    public void onCreate() {
        this.uploadDate = LocalDateTime.now();
    }
}