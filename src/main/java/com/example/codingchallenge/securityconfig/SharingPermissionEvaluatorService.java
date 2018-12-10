package com.example.codingchallenge.securityconfig;


import com.example.codingchallenge.model.Operation;
import com.example.codingchallenge.model.Product;
import com.example.codingchallenge.model.ProductSharingStatement;
import com.example.codingchallenge.model.Relation;
import com.example.codingchallenge.service.ProductService;
import com.example.codingchallenge.service.ProductSharingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SharingPermissionEvaluatorService implements SharingPermissionEvaluator  {

    @Autowired
    private ProductSharingService productSharingService;

    @Autowired
    private ProductService productService;
    /**
     * Method check if user has permission to execute requested operation with specific object
     * based on object id.
     * For a specific object id method check ProductSharingStatement
     * @param objectId - requested target object id (eg. Product id)
     * @param targetType - type of target object (eg. PRODUCT (uppercase))
     * @param auth - authentication object
     * @param permission - allowed permission (operation) for accessing object
     */
    public boolean SharingPermission(Authentication auth, String objectId, String targetType, String permission) {
        // get user organization id - accessingOrgId for ProductSharingStatement object
        CustomPrincipal principal = (CustomPrincipal) auth.getPrincipal();
        String accessingOrgId = principal.getUser().getOrganizationId();


        Product product = productService.FindById(objectId);

        // if accessing product from same organization - no need to check for sharing statements
        if (product.getOrganizationId().equals(accessingOrgId)){
            return true;
        }

        // get product sharing statement based on accesingOrgId and allowed accessing object permission
        List<ProductSharingStatement> productSharingStatements =
                productSharingService.FindProductSharingByAccessingOrgIdAndOperation(accessingOrgId, Operation.valueOf(permission));


        for (ProductSharingStatement productSharingStatement : productSharingStatements) {
            if (productSharingStatement.getSharingOrgId().equals(product.getOrganizationId())){
                // check other productSharingStatement attributes if they exist
                if(productSharingStatement.getPrice() >= 0) {
                    return CheckRelationExpression(product.getPrice(), productSharingStatement.getRelation(),
                                                   productSharingStatement.getPrice());
                }
                if (productSharingStatement.getQuantity() >= 0) {
                    return CheckRelationExpression(product.getQuantity(), productSharingStatement.getRelation(),
                                                   productSharingStatement.getQuantity());
                }
                return true;
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
