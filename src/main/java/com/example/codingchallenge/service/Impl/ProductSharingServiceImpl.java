package com.example.codingchallenge.service.Impl;

import com.example.codingchallenge.model.Operation;
import com.example.codingchallenge.model.ProductSharingStatement;
import com.example.codingchallenge.repository.ProductSharingRepository;
import com.example.codingchallenge.service.ProductSharingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductSharingServiceImpl implements ProductSharingService {

    @Autowired
    private ProductSharingRepository productSharingRepository;

    @Override
    public void CreateProductSharingStatement(ProductSharingStatement productSharingStatement) {
        String id = UUID.randomUUID().toString();
        productSharingStatement.setId(id);
        productSharingRepository.CreateSharingStatement(productSharingStatement);
    }

    @Override
    public List<ProductSharingStatement> FindProductSharingByAccessingOrgIdAndOperation(String accessingOrgId, Operation operation) {
        return productSharingRepository.FindSharingStatements(accessingOrgId, operation);
    }

    @Override
    public boolean ApproveSharingStatement(String sharingStatementId) {
        return productSharingRepository.ApproveSharingStatement(sharingStatementId);
    }

    @Override
    public boolean ForbidSharingStatement(String sharingStatementId) {
        return productSharingRepository.ForbidSharingStatement(sharingStatementId);
    }

    @Override
    public ProductSharingStatement FindById(String id) {
        return productSharingRepository.FindById(id);
    }
}
