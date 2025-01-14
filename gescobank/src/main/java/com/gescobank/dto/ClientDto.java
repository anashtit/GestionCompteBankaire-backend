package com.gescobank.dto;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ClientDto {



    private String nom;
    private String prenom;


    private Date birthDay;
    private String telephone;

    private String email;

    private String address;

//GETTERS
    public String getPrenom() {
        return prenom;
    }
    public String getNom() {
        return nom;
    }
    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getTelephone() {
        return telephone;
    }
    public Date getBirthDay() {
        return birthDay;
    }
}
