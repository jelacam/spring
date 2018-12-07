package com.example.codingchallenge.repository.Impl;

import com.example.codingchallenge.model.AdminRole;
import com.example.codingchallenge.model.Role;
import com.example.codingchallenge.repository.RoleRepository;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

    private String driver = "org.voltdb.jdbc.Driver";
    private String url = "jdbc:voltdb://172.25.50.222:21212";

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
    public void CreateAdminRole(AdminRole adminRole) {
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url);
            CallableStatement procedure = connection.prepareCall("{call ADMINROLE.insert(?, ?, ?)}");
            procedure.setString(1, adminRole.getId());
            procedure.setString(2, adminRole.getRoleId());
            procedure.setString(3, adminRole.getAdminId());

            procedure.executeQuery();

            procedure.close();
            connection.close();

        }
        catch (Exception e) {
            e.printStackTrace();

        }
    }
}
