package com.gescobank.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gescobank.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type" ,discriminatorType = DiscriminatorType.INTEGER)
@JsonIgnoreProperties({"hibernateLazyInitializer"})

public abstract class CompteBancaire implements Serializable {

    @Id @GeneratedValue (strategy = GenerationType.AUTO)
     private Long id;

    @Column(nullable = false)
    private Double balance;

    @Column(nullable = false)
    private String numCompte;


    @Column(nullable = false)
    private String devise = "CFA";


    @Column(nullable = false)
    private AccountStatus status ;

    private Date createdAte = new Date();

    @Column(nullable = false)
    @ManyToOne
    private Client client;

    @JsonBackReference
    @OneToMany(mappedBy = "compte")
    Collection<Operation> operations =new ArrayList<>() ;


//GETTERS SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public String getNumCompte() {
        return numCompte;
    }

    public void setNumCompte(String numCompte) {
        this.numCompte = numCompte;
    }

    public Date getCreatedAte() {
        return createdAte;
    }

    public void setCreatedAte(Date createdAte) {
        this.createdAte = createdAte;
    }

    public String getDevise() {
        return devise;
    }

    public void setDevise(String devise) {
        this.devise = devise;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

}
