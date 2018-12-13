package com.example.codingchallenge.repository.Impl;

import com.example.codingchallenge.model.Product;
import com.example.codingchallenge.model.SharingStatementQuery;
import com.example.codingchallenge.repository.ProductRepository;
import org.springframework.stereotype.Repository;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private String driver = "org.voltdb.jdbc.Driver";
    private String url = "jdbc:voltdb://172.25.50.222:21212";

    private String select = "SELECT id, name, description, price, quantity, organizationId FROM PRODUCT";
    private String selectById = "SELECT id, name, description, price, quantity, organizationId FROM PRODUCT WHERE id = ':id'";

    @Override
    public void CreateProduct(Product product) {
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url);
            CallableStatement procedure = connection.prepareCall("{call PRODUCT.insert(?, ?, ?, ?, ?, ?)}");
            procedure.setString(1, product.getId());
            procedure.setString(2, product.getName());
            procedure.setString(3, product.getDescription());
            procedure.setDouble(4, product.getPrice());
            procedure.setString(5, product.getOrganizationId());
            procedure.setInt(6, product.getQuantity());
            procedure.executeQuery();

            procedure.close();
            connection.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> GetAllProducts() {
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url);
            Statement query = connection.createStatement();
            ResultSet results = query.executeQuery(select);

            List<Product> products = new ArrayList<>();
            while(results.next()){
                Product product = new Product();
                product.setId(results.getString("id"));
                product.setName(results.getString("name"));
                product.setDescription(results.getString("description"));
                product.setPrice(results.getDouble("price"));
                product.setOrganizationId(results.getString("organizationId"));
                product.setQuantity(results.getInt("quantity"));
                products.add(product);
            }

            results.close();
            query.close();
            connection.close();
            return products;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean UpdateProduct(Product product) {
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url);
            CallableStatement procedure = connection.prepareCall("{call PRODUCT.update(?, ?, ?, ?, ?, ?, ?)}");
            procedure.setString(1, product.getId());
            procedure.setString(2, product.getName());
            procedure.setString(3, product.getDescription());
            procedure.setDouble(4, product.getPrice());
            procedure.setString(5, product.getOrganizationId());
            procedure.setInt(6, product.getQuantity());
            procedure.setString(7, product.getId());

            procedure.executeQuery();

            procedure.close();
            connection.close();
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean DeleteProduct(String id) {
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url);
            CallableStatement procedure = connection.prepareCall("{call PRODUCT.delete(?)}");
            procedure.setString(1, id);

            procedure.executeQuery();

            procedure.close();
            connection.close();
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Product FindById(String id) {
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url);
            Statement query = connection.createStatement();
            ResultSet results = query.executeQuery(selectById.replace(":id", id));

            Product product = new Product();
            while(results.next()){

                product.setId(results.getString("id"));
                product.setName(results.getString("name"));
                product.setDescription(results.getString("description"));
                product.setPrice(results.getDouble("price"));
                product.setOrganizationId(results.getString("organizationId"));
                product.setQuantity(results.getInt("quantity"));

            }

            results.close();
            query.close();
            connection.close();
            return product;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Product> GetAllProducts(List<SharingStatementQuery> sharingStatementQueries, String accessingOrgId) {
        try {
            // create select query
            String selectQuery = select.concat(" WHERE organizationId = '" + accessingOrgId+"'");
            for (SharingStatementQuery statementQuery: sharingStatementQueries) {
                selectQuery = selectQuery.concat(" OR (organizationId = '" + statementQuery.getSharingOrgId() + "'");
                if (statementQuery.getValue() != null && statementQuery.getRelation() != null && statementQuery.getAttribute() != null){
                    selectQuery = selectQuery.concat(" AND " + statementQuery.getAttribute() + " " + statementQuery.getRelation() + " " + statementQuery.getValue());
                }
                selectQuery = selectQuery.concat(" )");
            }

            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url);
            Statement query = connection.createStatement();
            ResultSet results = query.executeQuery(selectQuery);

            List<Product> products = new ArrayList<>();
            while(results.next()){
                Product product = new Product();
                product.setId(results.getString("id"));
                product.setName(results.getString("name"));
                product.setDescription(results.getString("description"));
                product.setPrice(results.getDouble("price"));
                product.setOrganizationId(results.getString("organizationId"));
                product.setQuantity(results.getInt("quantity"));
                products.add(product);
            }

            results.close();
            query.close();
            connection.close();
            return products;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
