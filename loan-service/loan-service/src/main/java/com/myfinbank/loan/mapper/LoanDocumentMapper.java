package com.myfinbank.loan.mapper;

import com.myfinbank.loan.dto.LoanDocumentResponse;
import com.myfinbank.loan.entity.LoanDocument;

public class LoanDocumentMapper {
    private LoanDocumentMapper() {}

    public static LoanDocumentResponse toResponse(LoanDocument document) {
        return LoanDocumentResponse.builder()
                .id(document.getId())
                .loanId(document.getLoanId())
                .documentType(document.getDocumentType())
                .fileName(document.getFileName())
                .filePath(document.getFilePath())
                .uploadDate(document.getUploadDate())
                .build();
    }
}
