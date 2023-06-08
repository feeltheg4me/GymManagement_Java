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
import tn.edu.esprit.entities.Payment;
import tn.edu.esprit.entities.Personne;
import tn.edu.esprit.utils.DataSource;

/**
 *
 * @author abdelazizmezri
 */
public class ServicePayment implements IServicePayment<Payment>{
    
    private DataSource ds = DataSource.getInstance();

    @Override
    public void ajouter(Payment p) throws SQLException{
        String req = 
                "INSERT INTO `payment` (`nom`, `prenom`) "
                + "VALUES ('"+p.getPrice()+"', '"+p.getTrainingType()+"', '"+p.getDatePayement()+"','"+ p.getMemberName() +"', '"+ p.getMemberId() +"' )";
        Statement st = ds.getCnx().createStatement();
        st.executeUpdate(req);
    }
    
    @Override
    public void modifier(Payment m) throws SQLException{
        String req = "UPDATE `payment` SET `datePayement` = '"+m.getDatePayement()+"', `trainingType` = '"+m.getTrainingType()+"', `price` = '"+m.getPrice()+"' WHERE `payment`.`id` = "+m.getId();
        Statement st = ds.getCnx().createStatement();
        st.executeUpdate(req);
    }

    @Override
    public void supprimer(int id) throws SQLException{
        String req = "DELETE FROM `payment` WHERE id ="+id;
        Statement st = ds.getCnx().createStatement();
        st.executeUpdate(req);
    }

    @Override
    public List<Payment> getAll() throws SQLException{
        List<Payment> list = new ArrayList<>();
        
        String req = "Select * from payment";
        Statement st = ds.getCnx().createStatement();
        ResultSet rs = st.executeQuery(req);
        while(rs.next()){
            Payment p = new Payment(rs.getInt("id"), rs.getInt("memberId"), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
            list.add(p);
        }
        
        return list;
    }
    
    
}
