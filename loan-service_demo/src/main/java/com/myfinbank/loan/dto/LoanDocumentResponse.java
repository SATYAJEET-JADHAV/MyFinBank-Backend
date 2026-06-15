package com.myfinbank.loan.dto;

import com.myfinbank.loan.enums.DocumentType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanDocumentResponse {

    private Long id;
    private Long loanId;
    private DocumentType documentType;
    private String fileName;
    private String filePath;
    private LocalDateTime uploadDate;
}