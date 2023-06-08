/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author abdelazizmezri
 */
public class DataSource {
    
    private static DataSource instance;
    
    private Connection cnx;
    
    private final String USER = "root";
    private final String PASSWORD = "";
    private final String URL = "jdbc:mysql://localhost/gymdb";

    private DataSource() {
        try {
            cnx = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("DB Connected !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static DataSource getInstance() {
        if(instance == null)
            instance = new DataSource();
        return instance;
    }

    public Connection getCnx() {
        try
        {
        return cnx;
        }catch (Exception e){e.printStackTrace();}
        return null;
    }
    
}
