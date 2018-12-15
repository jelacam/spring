package com.example.codingchallenge.securityconfig.permisson_evaluator;


import com.example.codingchallenge.model.Operation;
import com.example.codingchallenge.model.Product;
import com.example.codingchallenge.model.ProductSharingStatement;
import com.example.codingchallenge.model.Relation;
import com.example.codingchallenge.securityconfig.CustomPrincipal;
import com.example.codingchallenge.securityconfig.PermissionEvaluator;
import com.example.codingchallenge.service.ProductService;
import com.example.codingchallenge.service.ProductSharingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductPermissionEvaluator implements PermissionEvaluator {

    @Autowired
    private ProductSharingService productSharingService;

    @Autowired
    private ProductService productService;
    /**
     * Method check if user has permission to execute requested operation with specific object
     * based on object id.
     * @param objectId - requested target object id (eg. Product id)
     * @param targetType - type of target object (eg. PRODUCT)
     * @param auth - authentication object
     * @param permission - allowed permission (operation) for accessing object (eg. READ)
     */
    @Override
    public boolean SharingPermission(Authentication auth, String objectId, String targetType, String permission) {
        // get user organization id - accessingOrgId for ProductSharingStatement object
        CustomPrincipal principal = (CustomPrincipal) auth.getPrincipal();
        String accessingOrgId = principal.getUser().getOrganizationId();


        Product product = productService.FindById(objectId);
        if (product == null) {
            return false;
        }
        if (product.getOrganizationId() == null) { return false; }
        // if accessing product from same organization - no need to check for sharing statements
        if (product.getOrganizationId().equals(accessingOrgId)){ return true; }

        // get product sharing statement based on accesingOrgId and allowed accessing object permission
        List<ProductSharingStatement> productSharingStatements =
                productSharingService.FindProductSharingByAccessingOrgIdAndOperation(accessingOrgId, Operation.valueOf(permission));


        for (ProductSharingStatement productSharingStatement : productSharingStatements) {
            if (productSharingStatement.getSharingOrgId().equals(product.getOrganizationId())){
                // check other productSharingStatement attributes if they exist
                boolean priceEvaluation = true;
                boolean quantityEvaluation = true;
                // if relation null - organization share all data, no need to check price and quantity
                if (productSharingStatement.getRelation() != null) {
                    if (productSharingStatement.getPrice() > -1) {
                        priceEvaluation = CheckRelationExpression(product.getPrice(), productSharingStatement.getRelation(),
                                productSharingStatement.getPrice());
                    }
                    if (productSharingStatement.getQuantity() > -1) {
                        quantityEvaluation = CheckRelationExpression(product.getQuantity(), productSharingStatement.getRelation(),
                                productSharingStatement.getQuantity());
                    }
                }
                return priceEvaluation && quantityEvaluation;
            }
        }
        return false;
    }

    /**
     * Evaluate relation expression
     * @param data  - object value
     * @param relation - sharing statement relation
     * @param value - sharing statement object value
     */
    private boolean CheckRelationExpression(double data, Relation relation, double value){
        switch (relation) {
            case EQ:
            {
                return data == value;
            }
            case GT: {
                return data > value;
            }
            case GTE: {
                return  data >= value;
            }
            case LT: {
                return data < value;
            }
            case LTE: {
                return data <= value;
            }
            default: {
                return false;
            }
        }
    }

}
