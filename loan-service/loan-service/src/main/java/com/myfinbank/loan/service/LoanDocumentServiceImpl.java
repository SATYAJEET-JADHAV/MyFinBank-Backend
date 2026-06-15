package com.myfinbank.loan.service;

import com.myfinbank.loan.dto.LoanDocumentResponse;
import com.myfinbank.loan.entity.LoanDocument;
import com.myfinbank.loan.enums.DocumentType;
import com.myfinbank.loan.exception.DocumentNotFoundException;
import com.myfinbank.loan.exception.LoanNotFoundException;
import com.myfinbank.loan.mapper.LoanDocumentMapper;
import com.myfinbank.loan.repository.LoanApplicationRepository;
import com.myfinbank.loan.repository.LoanDocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanDocumentServiceImpl implements LoanDocumentService {
    private final LoanDocumentRepository loanDocumentRepository;
    private final LoanApplicationRepository loanApplicationRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public LoanDocumentResponse uploadDocument(Long loanId, DocumentType documentType, MultipartFile file) {
        if (!loanApplicationRepository.existsById(loanId)) {
            throw new LoanNotFoundException("Loan not found with ID: " + loanId);
        }
        try {
            String originalFileName = file.getOriginalFilename() == null ? "document" : file.getOriginalFilename().replaceAll("[^a-zA-Z0-9._-]", "_");
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
            String safeFileName = timestamp + "_" + originalFileName;
            Path loanFolder = Paths.get(uploadDir, "loan-" + loanId);
            Files.createDirectories(loanFolder);
            Path targetPath = loanFolder.resolve(safeFileName);
            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

            LoanDocument document = LoanDocument.builder()
                    .loanId(loanId)
                    .documentType(documentType)
                    .fileName(safeFileName)
                    .filePath(targetPath.toString())
                    .build();

            return LoanDocumentMapper.toResponse(loanDocumentRepository.save(document));
        } catch (Exception exception) {
            throw new RuntimeException("Could not upload document: " + exception.getMessage());
        }
    }

    @Override
    public LoanDocumentResponse getDocumentById(Long id) {
        LoanDocument document = loanDocumentRepository.findById(id)
                .orElseThrow(() -> new DocumentNotFoundException("Document not found with ID: " + id));
        return LoanDocumentMapper.toResponse(document);
    }

    @Override
    public List<LoanDocumentResponse> getDocumentsByLoanId(Long loanId) {
        if (!loanApplicationRepository.existsById(loanId)) {
            throw new LoanNotFoundException("Loan not found with ID: " + loanId);
        }
        return loanDocumentRepository.findByLoanId(loanId).stream().map(LoanDocumentMapper::toResponse).toList();
    }
}
