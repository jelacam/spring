package com.example.codingchallenge.repository.Impl;

import com.example.codingchallenge.model.Admin;
import com.example.codingchallenge.repository.AdminRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class AdminRepositoryImpl implements AdminRepository {

    private String driver = "org.voltdb.jdbc.Driver";
    private String url = "jdbc:voltdb://172.25.50.222:21212";

    private String select = "SELECT id, firstName, lastName, email, organizationId FROM ADMIN WHERE id = ':id'";
    private String selectByUsername = "SELECT id, firstName, lastName, email, password, organizationId FROM ADMIN WHERE email = ':email'";

    @Override
    public boolean createAdminUser(Admin admin) {

        try {
            // load driver, create connection
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url);
            CallableStatement procedure = connection.prepareCall("{call ADMIN.insert(?, ?, ?, ?, ?, ?)}");
            procedure.setString(1, admin.getId());
            procedure.setString(2, admin.getFirstName());
            procedure.setString(3, admin.getLastName());
            procedure.setString(4, admin.getEmail());
            procedure.setString(5, admin.getPassword());
            procedure.setString(6, admin.getOrganizationId());

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
    public Admin updateAdmin(Admin admin) {
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url);
            CallableStatement procedure = connection.prepareCall("{call ADMIN.update(?, ?, ?, ?, ?, ?, ?)}");
            procedure.setString(1, admin.getId());
            procedure.setString(2, admin.getFirstName());
            procedure.setString(3, admin.getLastName());
            procedure.setString(4, admin.getEmail());
            procedure.setString(5, admin.getPassword());
            procedure.setString(6, admin.getOrganizationId());
            procedure.setString(7, admin.getId());

            procedure.executeQuery();
            Admin updatedAdmin = new Admin();
            updatedAdmin = findAdmin(admin.getId());

            procedure.close();
            connection.close();
            return updatedAdmin;
        }
        catch (Exception e){
            e.printStackTrace();
            return admin;
        }
    }

    @Override
    public boolean deleteAdmin(String id) {
        try{
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url);
            CallableStatement procedure = connection.prepareCall("{call ADMIN.delete(?)}");
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
    public Admin findAdmin(String id) {
        try{
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url);
            Statement query = connection.createStatement();
            ResultSet results = query.executeQuery(select.replace(":id", id));

            Admin admin = new Admin();
            while(results.next()){
                admin.setId(results.getString("id"));
                admin.setFirstName(results.getString("firstName"));
                admin.setLastName(results.getString("lastName"));
                admin.setOrganizationId(results.getString("organizationId"));
                admin.setEmail(results.getString("email"));
            }
            results.close();
            query.close();
            connection.close();
            return admin;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Admin findAdminByUsername(String email) {
        try{
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url);
            Statement query = connection.createStatement();
            ResultSet results = query.executeQuery(selectByUsername.replace(":email", email));

            Admin admin = new Admin();
            while(results.next()){
                admin.setId(results.getString("id"));
                admin.setFirstName(results.getString("firstName"));
                admin.setLastName(results.getString("lastName"));
                admin.setOrganizationId(results.getString("organizationId"));
                admin.setEmail(results.getString("email"));
                admin.setPassword(results.getString(("password")));
            }
            results.close();
            query.close();
            connection.close();
            return admin;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
