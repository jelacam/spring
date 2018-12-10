package com.example.codingchallenge.repository.Impl;

import com.example.codingchallenge.model.Organization;
import com.example.codingchallenge.repository.OrganizationRepository;
import jdk.nashorn.internal.codegen.CompilerConstants;
import org.springframework.stereotype.Repository;

import java.sql.DriverManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrganizationRepositoryImpl implements OrganizationRepository {

    private String driver = "org.voltdb.jdbc.Driver";
    private String url = "jdbc:voltdb://172.25.50.222:21212";

    public OrganizationRepositoryImpl() {

    }

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

}
