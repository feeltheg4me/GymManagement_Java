/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.entities;

import tn.edu.esprit.utils.RegexValidation;
import java.sql.Date;



/**
 *
 * @author abdelazizmezri
 */
public class Admin {
    
    @RegexValidation(pattern = ".d{4,}", message = "Invalid login !")
    private String login;
    @RegexValidation(pattern = ".d{6,}", message = "Password too short !")
    private String password;

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Admin(String login, String password) {
        this.login = login;
        this.password = password;
    }
    
    
    
}
