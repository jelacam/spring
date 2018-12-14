package com.example.codingchallenge.repository.Impl;

import com.example.codingchallenge.model.Entity;
import com.example.codingchallenge.model.Operation;
import com.example.codingchallenge.model.Permission;
import com.example.codingchallenge.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PermissionRepositoryImpl implements PermissionRepository {

    @Value("${voltDb.driver}")
    private String driver;

    @Value("${voltDb.url}")
    private String url;

    private String select = "SELECT id, entity, operation, roleId FROM PERMISSION WHERE id = ':id'";
    private String selectByRoleId = "SELECT id, entity, operation, roleId FROM PERMISSION WHERE roleId = ':roleId'";
    private String selectPermissionByAdminId = "SELECT permission.id, permission.entity, permission.operation, " +
            " permission.roleid from permission inner join role on permission.roleid = role.id inner join adminrole on" +
            " adminrole.roleid = role.id where adminrole.adminid = ':adminId'";

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

    @Override
    public List<Permission> FindByRoleId(String id) {
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url);
            Statement query = connection.createStatement();
            ResultSet results = query.executeQuery(selectByRoleId.replace(":roleId", id));

            List<Permission> permissions = new ArrayList<>();

            while (results.next()){
                Permission permission = new Permission();
                permission.setId(results.getString("id"));
                permission.setEntity(Entity.values()[results.getShort("entity")]);
                permission.setOperation(Operation.values()[results.getShort("operation")]);
                permission.setRoleId(results.getString("roleId"));
                permissions.add(permission);
            }
            results.close();
            query.close();
            connection.close();
            return permissions;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Permission> FindByAdminId(String id) {
        try{
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url);
            Statement query = connection.createStatement();
            ResultSet results = query.executeQuery(selectPermissionByAdminId.replace(":adminId", id));
            List<Permission> permissions = new ArrayList<>();

            while(results.next()){
                Permission permission = new Permission();
                permission.setId(results.getString("id"));
                permission.setEntity(Entity.values()[results.getShort("entity")]);
                permission.setOperation(Operation.values()[results.getShort("operation")]);
                permission.setRoleId(results.getString("roleId"));
                permissions.add(permission);
            }

            results.close();
            query.close();
            connection.close();
            return permissions;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
