package com.example.codingchallenge.repository.Impl;

import com.example.codingchallenge.model.Admin;
import com.example.codingchallenge.model.Entity;
import com.example.codingchallenge.model.Operation;
import com.example.codingchallenge.model.Permission;
import com.example.codingchallenge.repository.PermissionRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class PermissionRepositoryImpl implements PermissionRepository {

    private String driver = "org.voltdb.jdbc.Driver";
    private String url = "jdbc:voltdb://172.25.50.222:21212";

    private String select = "SELECT id, entity, operation, roleId FROM PERMISSION WHERE id = ':id'";
    @Override
    public boolean CreatePermission(Permission permission) {
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url);
            CallableStatement procedure = connection.prepareCall("{call PERMISSION.insert(?, ?, ?, ?)}");
            procedure.setString(1, permission.getId());
            procedure.setShort(2, permission.getEntity().Value());
            procedure.setShort(3, permission.getOperation().Value());
            procedure.setString(4, permission.getRoleId());

            procedure.executeQuery();

            procedure.close();
            connection.close();
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return  false;
        }
    }

    @Override
    public Permission FindById(String id) {
        try{
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url);
            Statement query = connection.createStatement();
            ResultSet results = query.executeQuery(select.replace(":id", id));

            Permission permission = new Permission();
            while(results.next()){
                permission.setId(results.getString("id"));
                permission.setEntity(Entity.values()[results.getShort("entity")]);
                permission.setOperation(Operation.values()[results.getShort("operation")]);
                permission.setRoleId(results.getString("roleId"));
            }

            results.close();
            query.close();
            connection.close();
            return permission;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
