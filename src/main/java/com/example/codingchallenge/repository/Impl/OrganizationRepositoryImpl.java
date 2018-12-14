package com.example.codingchallenge.repository.Impl;

import com.example.codingchallenge.model.Organization;
import com.example.codingchallenge.repository.OrganizationRepository;
import jdk.nashorn.internal.codegen.CompilerConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.DriverManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrganizationRepositoryImpl implements OrganizationRepository {

    @Value("${voltDb.driver}")
    private String driver;

    @Value("${voltDb.url}")
    private String url;

    public List<Organization> findAll(){
        String sql = "SELECT id, name, master FROM Organization";
        try {
            // load driver, create connection
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url);

            Statement query = connection.createStatement();
            ResultSet results = query.executeQuery(sql);

            final List<Organization> organizations = new ArrayList<>();
            while(results.next()){
                final Organization organization = new Organization();
                organization.setId(results.getString("id"));
                organization.setName(results.getString("name"));
                int master = results.getInt("master");
                organization.setMaster(master == 1);
                organizations.add(organization);
            }

            // close query, results, connection ...
            query.close();
            results.close();
            connection.close();
            return organizations;
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void Create(Organization organization) {
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url);
            CallableStatement procedure = connection.prepareCall("{call ORGANIZATION.insert(?, ?, ?)}");
            procedure.setString(1, organization.getId());
            procedure.setString(2, organization.getName());
            procedure.setShort(3, (short) (organization.getMaster() ? 1 : 0));

            procedure.executeQuery();

            procedure.close();
            connection.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean UpdateOrganization(Organization organization) {
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url);
            CallableStatement procedure = connection.prepareCall("{call ORGANIZATION.update(?, ?, ?, ?)}");
            procedure.setString(1, organization.getId());
            procedure.setString(2, organization.getName());
            procedure.setShort(3, (short) (organization.getMaster() ? 1 : 0));
            procedure.setString(4, organization.getId());

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
    public boolean DeleteOrganization(String id) {
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url);
            CallableStatement procedure = connection.prepareCall("{call ORGANIZATION.delete(?)}");
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

}
