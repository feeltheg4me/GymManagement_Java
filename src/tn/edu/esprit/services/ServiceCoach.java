/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tn.edu.esprit.entities.Coach;
import tn.edu.esprit.entities.Member;
import tn.edu.esprit.entities.Payment;
import tn.edu.esprit.entities.Personne;
import tn.edu.esprit.utils.DataSource;

/**
 *
 * @author abdelazizmezri
 */
public class ServiceCoach implements IServiceCoach<Coach>{
    
    private DataSource ds = DataSource.getInstance();

    @Override
    public void ajouter(Coach c) throws SQLException{
        String req = 
                "INSERT INTO `coach` (`nom`) "
                + "VALUES ('"+c.getNom()+"')";
        Statement st = ds.getCnx().createStatement();
        st.executeUpdate(req);
    }
    @Override
    public void modifier(Coach c) throws SQLException{
        String req = "UPDATE `coach` SET `nom` = '"+c.getNom()+"' WHERE `coach`.`id` = "+c.getId();
        Statement st = ds.getCnx().createStatement();
        st.executeUpdate(req);
    }

    @Override
    public void supprimer(int id) throws SQLException{
        String req = "DELETE FROM `coach` WHERE id ="+id;
        Statement st = ds.getCnx().createStatement();
        st.executeUpdate(req);
    }

    @Override
    public List<Coach> getAll() throws SQLException{
        List<Coach> list = new ArrayList<>();
        
        String req = "Select * from coach";
        Statement st = ds.getCnx().createStatement();
        ResultSet rs = st.executeQuery(req);
        while(rs.next()){
            Coach c = new Coach(rs.getInt("id"), rs.getString("coachId"),
                        rs.getString("nom"), rs.getInt("phone"),
                        rs.getString("status"), rs.getString("adress"),
                        rs.getString("gender"));

            list.add(c);
        }
        
        return list;
    }
    
    
}
