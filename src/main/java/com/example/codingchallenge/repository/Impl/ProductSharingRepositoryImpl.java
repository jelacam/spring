package com.example.codingchallenge.repository.Impl;

import com.example.codingchallenge.model.Operation;
import com.example.codingchallenge.model.ProductSharingStatement;
import com.example.codingchallenge.model.Relation;
import com.example.codingchallenge.repository.ProductSharingRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@Repository
public class ProductSharingRepositoryImpl implements ProductSharingRepository {

    private String driver = "org.voltdb.jdbc.Driver";
    private String url = "jdbc:voltdb://172.25.50.222:21212";

    private String selectSharingStatementByAccesssingOrgId = "SELECT * FROM PRODUCTSHARINGSTATEMENT WHERE accessingOrgId = ':accessingOrgId' " +
            "AND approved = 1 AND operation = :operation";
    private String selectById = "SELECT * FROM PRODUCTSHARINGSTATEMENT WHERE id = ':id'";

    @Override
    public void CreateSharingStatement(ProductSharingStatement productSharingStatement) {
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url);
            CallableStatement procedure = connection.prepareCall("{call PRODUCTSHARINGSTATEMENT.insert(?, ?, ?, ?, ?, ?, ?, ?)}");
            procedure.setString(1, productSharingStatement.getId());
            procedure.setString(2, productSharingStatement.getSharingOrgId());
            procedure.setString(3, productSharingStatement.getAccessingOrgId());
            if (productSharingStatement.getQuantity() > 0) {
                procedure.setInt(4, productSharingStatement.getQuantity());
            }
            if (productSharingStatement.getPrice() > 0) {
                procedure.setFloat(5, productSharingStatement.getPrice());
            }
            if (productSharingStatement.getRelation() != null) {
                procedure.setShort(6, productSharingStatement.getRelation().Value());
            }
            if (productSharingStatement.getOperation() != null) {
                procedure.setShort(7, productSharingStatement.getOperation().Value());
            }
            procedure.setShort(8, (short) (productSharingStatement.getApproved() ? 1 : 0));

            procedure.executeQuery();
            procedure.close();
            connection.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<ProductSharingStatement> FindSharingStatements(String accessingOrgId, Operation operation) {
        try {
            String queryUrl = selectSharingStatementByAccesssingOrgId.replace(":accessingOrgId", accessingOrgId)
                .replace(":operation", String.valueOf(operation.Value()));

            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url);
            Statement query = connection.createStatement();
            ResultSet results = query.executeQuery(queryUrl);

            List<ProductSharingStatement> productSharingStatements = new ArrayList<>();
            while(results.next()){
                ProductSharingStatement productSharingStatement = new ProductSharingStatement();
                productSharingStatement.setId(results.getString("id"));
                productSharingStatement.setSharingOrgId(results.getString("sharingOrgId"));
                productSharingStatement.setAccessingOrgId(results.getString("accessingOrgId"));
                if (results.getFloat("price") >= 0) {
                    productSharingStatement.setPrice(results.getFloat("price"));
                }
                if (results.getInt("quantity") >= 0) {
                    productSharingStatement.setQuantity(results.getInt("quantity"));
                }
                if (results.getShort("relation") >= 0) {
                    productSharingStatement.setRelation(Relation.values()[results.getShort("relation")]);
                }
                productSharingStatement.setApproved(results.getShort("approved") == 1);
                productSharingStatement.setOperation(Operation.values()[results.getShort("operation")]);

                productSharingStatements.add(productSharingStatement);
            }
            results.close();
            query.close();
            connection.close();
            return productSharingStatements;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean ApproveSharingStatement(String sharingStatementId) {
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url);
            CallableStatement procedure = connection.prepareCall("{call PRODUCTSHARINGSTATEMENT.update(?, ?)}");
            procedure.setShort("approved", (short)1);
            procedure.setString("id", sharingStatementId);
            procedure.executeQuery();

            procedure.close();
            connection.close();

            return  true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean ForbidSharingStatement(String sharingStatementId) {
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url);
            CallableStatement procedure = connection.prepareCall("{call PRODUCTSHARINGSTATEMENT.update(?, ?, ?, ?, ?, ?, ?, ?. ?)}");
            procedure.setShort(9, (short) 0);
            procedure.executeQuery();

            procedure.close();
            connection.close();

            return  true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ProductSharingStatement FindById(String id) {
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url);
            Statement query = connection.createStatement();
            ResultSet results = query.executeQuery(selectById.replace(":id", id));

            ProductSharingStatement productSharingStatement = new ProductSharingStatement();
            while(results.next()){

                productSharingStatement.setId(results.getString("id"));
                productSharingStatement.setSharingOrgId(results.getString("sharingOrgId"));
                productSharingStatement.setAccessingOrgId(results.getString("accessingOrgId"));
                if (results.getFloat("price") >= 0) {
                    productSharingStatement.setPrice(results.getFloat("price"));
                }
                if (results.getInt("quantity") >= 0) {
                    productSharingStatement.setQuantity(results.getInt("quantity"));
                }
                if (results.getShort("relation") >= 0) {
                    productSharingStatement.setRelation(Relation.values()[results.getShort("relation")]);
                }
                productSharingStatement.setApproved(results.getShort("approved") == 1);
                productSharingStatement.setOperation(Operation.values()[results.getShort("operation")]);

            }
            results.close();
            query.close();
            connection.close();
            return productSharingStatement;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
