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
import tn.edu.esprit.entities.Member;
import tn.edu.esprit.entities.Personne;
import tn.edu.esprit.utils.DataSource;

/**
 *
 * @author abdelazizmezri
 */
public class ServiceMember implements IServiceMember<Member>{
    
    private DataSource ds = DataSource.getInstance();

    @Override
    public void ajouter(Member m) throws SQLException{
        String req = "INSERT INTO `member` (`nom`, `prenom`) VALUES ('"+m.getNom()+"')";
        Statement st = ds.getCnx().createStatement();
        st.executeUpdate(req);
    }
    
    public void ajouter2(Member m) throws SQLException{
        String req = "INSERT INTO `member` (`nom`, `prenom`) VALUES (?,?)";
        PreparedStatement st = ds.getCnx().prepareStatement(req);
        st.setString(1, m.getNom());
        
        st.executeUpdate();
    }

    @Override
    public void modifier(Member m) throws SQLException{
        String req = "UPDATE `member` SET `nom` = '"+m.getNom()+"' WHERE `member`.`id` = "+m.getId();
        Statement st = ds.getCnx().createStatement();
        st.executeUpdate(req);
    }

    @Override
    public void supprimer(int id) throws SQLException{
        String req = "DELETE FROM `member` WHERE id ="+id;
        Statement st = ds.getCnx().createStatement();
        st.executeUpdate(req);
    }

    @Override
    public List<Member> getAll() throws SQLException{
        List<Member> list = new ArrayList<>();
        
        String req = "Select * from member";
        Statement st = ds.getCnx().createStatement();
        ResultSet rs = st.executeQuery(req);
        while(rs.next()){
            Member m = new Member(rs.getInt("id"),
                        rs.getString("memberId"),
                        rs.getString("nom"),
                        rs.getString("adress"),
                        rs.getString("gender"),
                        rs.getString("trainingType"),
                        rs.getInt("phone"),
                        rs.getDate("startDate"),
                        rs.getDate("endDate"),
                        rs.getDouble("price"),
                        rs.getString("status"));
            list.add(m);
        }
        
        return list;
    }
    
    
}
