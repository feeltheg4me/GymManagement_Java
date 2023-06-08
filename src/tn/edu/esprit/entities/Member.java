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
public class Member {
    
    private int id;
    private String memberId, adresse, gender, trainingType, status;
    @RegexValidation(pattern = "[A-Za-z]+", message = "Invalid name !")
    private String nom;
    @RegexValidation(pattern = "\\d{8}", message = "Invalid phone number !")
    private int phone;
    private Date startDate;
    private Date endDate;
    private double price;

    public String getMemberId() {
        return memberId;
    }

    public String getGender() {
        return gender;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public Member(int id, String memberId, String nom, String adresse, String gender, String trainingType, int phone, Date startDate, Date endDate, double price, String status) {
        this.id = id;
        this.memberId = memberId;
        this.nom = nom;
        this.adresse = adresse;
        this.gender = gender;
        this.trainingType = trainingType;
        this.phone = phone;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.status = status;
    }

    public Member(String memberId, String nom, String adresse, String genre, String trainingType, int phone, Date startDate, Date endDate, double price, String status) {
        this.memberId = memberId;
        this.nom = nom;
        this.adresse = adresse;
        this.gender = genre;
        this.trainingType = trainingType;
        this.phone = phone;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

   
    public String getTrainingType() {
        return trainingType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public double getPrice() {
        return price;
    }

    public void setTrainingType(String trainingType) {
        this.trainingType = trainingType;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setPrice(double price) {
        this.price = price;
    }

   

    public int getPhone() {
        return phone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setGender(String genre) {
        this.gender = genre;
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
        final Member other = (Member) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
    
}
