/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.entities;

/**
 *
 * @author abdelazizmezri
 */
public class Payment {
    
    private int id, memberId;
    private String price, trainingType, memberName;
    private String datePayement;

    public Payment() {
    }

    public Payment(int memberId, String price, String trainingType, String memberName, String datePayement) {
        this.memberId = memberId;
        this.price = price;
        this.trainingType = trainingType;
        this.memberName = memberName;
        this.datePayement = datePayement;
    }

    public Payment(int id, int memberId, String price, String trainingType, String memberName, String datePayement) {
        this.id = id;
        this.memberId = memberId;
        this.price = price;
        this.trainingType = trainingType;
        this.memberName = memberName;
        this.datePayement = datePayement;
    }


    public int getId() {
        return id;
    }

  
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Payment{" + "id=" + id + ", memberId=" + memberId + ", price=" + price + ", trainingType=" + trainingType + ", memberName=" + memberName + ", datePayement=" + datePayement + '}';
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setTrainingType(String trainingType) {
        this.trainingType = trainingType;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public void setDatePayement(String datePayement) {
        this.datePayement = datePayement;
    }

    public int getMemberId() {
        return memberId;
    }

    public String getPrice() {
        return price;
    }

    public String getTrainingType() {
        return trainingType;
    }

    public String getMemberName() {
        return memberName;
    }

    public String getDatePayement() {
        return datePayement;
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
        final Payment other = (Payment) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
    
}
