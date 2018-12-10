package com.example.codingchallenge.service;

import com.example.codingchallenge.model.Operation;
import com.example.codingchallenge.model.ProductSharingStatement;

import java.util.List;

public interface ProductSharingService {
    void CreateProductSharingStatement(ProductSharingStatement productSharingStatement);
    List<ProductSharingStatement> FindProductSharingByAccessingOrgIdAndOperation(String accessingOrgId, Operation operation);
    boolean ApproveSharingStatement(String sharingStatementId);
    boolean ForbidSharingStatement(String sharingStatementId);
}
