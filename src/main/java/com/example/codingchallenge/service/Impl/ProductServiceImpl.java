package com.example.codingchallenge.service.Impl;

import com.example.codingchallenge.model.*;
import com.example.codingchallenge.repository.ProductRepository;
import com.example.codingchallenge.repository.ProductSharingRepository;
import com.example.codingchallenge.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductSharingRepository productSharingRepository;

    @Override
    public void CreateProduct(Product product) {
        String id = UUID.randomUUID().toString();
        product.setId(id);
        productRepository.CreateProduct(product);
    }

    @Override
    public List<Product> GetAllProducts(String accessingOrgId) {
        List<SharingStatementQuery> sharingStatementQueryList = EvaluateSharingStatements(accessingOrgId);
        return productRepository.GetAllProducts(sharingStatementQueryList, accessingOrgId);
    }

    @Override
    public boolean UpdateProduct(Product product) {
        return productRepository.UpdateProduct(product);
    }

    @Override
    public boolean DeleteProduct(String id) {
        return productRepository.DeleteProduct(id);
    }

    @Override
    public Product FindById(String id) {
        return productRepository.FindById(id);
    }


    private List<SharingStatementQuery> EvaluateSharingStatements(String accessingOrgId){
        String attribute, relation, value;
        List<SharingStatementQuery> sharingStatementQueryList = new ArrayList<>();
        List<ProductSharingStatement> productSharingStatements = productSharingRepository.FindSharingStatements(accessingOrgId, Operation.READ);
        for (ProductSharingStatement statement: productSharingStatements) {

            String sharingOrgId = statement.getSharingOrgId();

            if (statement.getRelation() != null) {
                relation = CastRelation(statement.getRelation());

                if (statement.getQuantity() >= 0) {
                    attribute = "quantity";
                    value = String.format("%s", statement.getQuantity());
                    SharingStatementQuery sharingStatementQuery = new SharingStatementQuery(attribute, relation, value, sharingOrgId);
                    sharingStatementQueryList.add(sharingStatementQuery);
                }
                if (statement.getPrice() >= 0) {
                    attribute = "price";
                    value = String.format("%s", statement.getPrice());
                    SharingStatementQuery sharingStatementQuery = new SharingStatementQuery(attribute, relation, value, sharingOrgId);
                    sharingStatementQueryList.add(sharingStatementQuery);
                }
            }
            else {
                SharingStatementQuery sharingStatementQuery = new SharingStatementQuery(null, null, null, sharingOrgId);
                sharingStatementQueryList.add(sharingStatementQuery);
            }
        }
        return sharingStatementQueryList;
    }

    private String CastRelation(Relation relation) {
        switch (relation) {
            case EQ:
                return "=";
            case GT:
                return ">";
            case GTE:
                return ">=";
            case LT:
                return "<";
            case LTE:
                return "<=";
            default:
                return null;
        }

    }
}
