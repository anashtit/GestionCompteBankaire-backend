package com.gescobank.services;

import com.gescobank.dto.ClientDto;
import com.gescobank.dto.CompteDto;
import com.gescobank.entities.Client;
import com.gescobank.entities.CompteBancaire;
import com.gescobank.entities.CompteCourant;
import com.gescobank.entities.CompteEpargne;

import java.util.List;

public interface CompteService {

    void createAccount(CompteDto compteDto);

    List<CompteEpargne> findCompteEpargne();

    List<CompteCourant> findCompteCourant();

    CompteBancaire findOne(String numCompte);

     boolean activeCompte(String numCompte);

    boolean suspendreCompte(String numCompte);

}
