package com.example.codingchallenge.repository.Impl;

import com.example.codingchallenge.model.AdminRole;
import com.example.codingchallenge.model.Role;
import com.example.codingchallenge.repository.PermissionRepository;
import com.example.codingchallenge.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

    @Value("${voltDb.driver}")
    private String driver;

    @Value("${voltDb.url}")
    private String url;

    private String selectAdminRoleByAdminId = "SELECT id, adminId, roleId FROM ADMINROLE WHERE adminId = ':adminId'";
    private String selectRole = "SELECT id, name, organizationId FROM ROLE WHERE id = ':id'";

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public boolean CreateRole(Role role) {
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url);
            CallableStatement procedure = connection.prepareCall("{call ROLE.insert(?, ?, ?)}");
            procedure.setString(1, role.getId());
            procedure.setString(2, role.getName());
            procedure.setString(3, role.getOrganizationId());

            procedure.executeQuery();

            procedure.close();
            connection.close();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Role FindRoleById(String id) {
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url);
            Statement query = connection.createStatement();
            ResultSet results = query.executeQuery(selectRole.replace(":id", id));

            Role role = new Role();
            while(results.isFirst()){
                role.setId(results.getString("id"));
                role.setName(results.getString("name"));
                role.setOrganizationId(results.getString("organizationId"));
            }
            role.setPermissions(permissionRepository.FindByRoleId(id));
            results.close();
            query.close();
            connection.close();
            return role;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void CreateAdminRole(AdminRole adminRole) {
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url);
            CallableStatement procedure = connection.prepareCall("{call ADMINROLE.insert(?, ?, ?)}");
            procedure.setString(1, adminRole.getId());
            procedure.setString(2, adminRole.getAdminId());
            procedure.setString(3, adminRole.getRoleId());

            procedure.executeQuery();

            procedure.close();
            connection.close();

        }
        catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Override
    public List<AdminRole> FindAdminRoleByAdminId(String adminId) {
        try{
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url);
            Statement query = connection.createStatement();
            ResultSet results = query.executeQuery(selectAdminRoleByAdminId.replace(":adminId", adminId));

            List<AdminRole> adminRoles = new ArrayList<>();
            while(results.next()){
                AdminRole adminRole = new AdminRole();
                adminRole.setId(results.getString("id"));
                adminRole.setAdminId(results.getString("adminId"));
                adminRole.setRoleId(results.getString("roleId"));
                adminRoles.add(adminRole);
            }

            results.close();
            query.close();
            connection.close();
            return adminRoles;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
