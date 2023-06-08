/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.entities;

import tn.edu.esprit.utils.RegexValidation;

/**
 *
 * @author abdelazizmezri
 */
public class Coach {
    
    private int id;
    private String coachId;
    @RegexValidation(pattern = "[A-Za-z]+", message = "Invalid name !")
    private String nom;
    @RegexValidation(pattern = "\\d{8}", message = "Invalid phone number !")
    private Integer phone;
    private String status;
    private String adress;
    private String gender;

    public Coach(int id, String coachId, String nom, Integer phone, String status, String adress, String gender) {
        this.id = id;
        this.coachId = coachId;
        this.nom = nom;
        this.phone = phone;
        this.status = status;
        this.adress = adress;
        this.gender = gender;
    }

    public Coach(String coachId, String nom, Integer phone, String status, String adress, String gender) {
        this.coachId = coachId;
        this.nom = nom;
        this.phone = phone;
        this.status = status;
        this.adress = adress;
        this.gender = gender;
    }

    public Coach() {
    }

    public String getCoachId() {
        return coachId;
    }

    public Integer getPhone() {
        return phone;
    }

    public String getStatus() {
        return status;
    }

    public String getAdress() {
        return adress;
    }

    public String getGender() {
        return gender;
    }

    public void setCoachId(String coachId) {
        this.coachId = coachId;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    
   

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Coach other = (Coach) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
    
}
