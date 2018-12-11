package com.example.codingchallenge.repository;

import com.example.codingchallenge.model.Operation;
import com.example.codingchallenge.model.ProductSharingStatement;

import java.util.List;

public interface ProductSharingRepository {
    void CreateSharingStatement(ProductSharingStatement productSharingStatement);
    List<ProductSharingStatement> FindSharingStatements(String accessingOrgId, Operation operation);
    boolean ApproveSharingStatement(String sharingStatementId);
    boolean ForbidSharingStatement(String sharingStatementId);

    ProductSharingStatement FindById(String id);
}
