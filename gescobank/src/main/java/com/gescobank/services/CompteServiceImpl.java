package com.gescobank.services;

import com.gescobank.dto.CompteDto;
import com.gescobank.entities.CompteBancaire;
import com.gescobank.entities.CompteCourant;
import com.gescobank.entities.CompteEpargne;
import com.gescobank.enums.AccountStatus;
import com.gescobank.repositories.ClientRepository;
import com.gescobank.repositories.CompteBancaireRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CompteServiceImpl implements CompteService{

    private final CompteBancaireRepository compteBancaireRepository;
    private final ClientRepository clientRepository;

    CompteServiceImpl (
            final CompteBancaireRepository compteBancaireRepository,
            final ClientRepository clientRepository
    ){

        this.clientRepository = clientRepository;
       this.compteBancaireRepository = compteBancaireRepository;
    }





    @Override
    public void createAccount(CompteDto compteDto) {

        Optional<Client> clientOpt =this.clientRepository.findById(compteDto.getClientId());
        if(clientOpt.isPresent() && (compteDto.getDecouvert() >0 && compteDto.getTauxInteret() == 0 )){

            CompteCourant compteCourant = new CompteCourant();
            compteCourant.setCreatdAte(new Date());
            compteCourant.setBalance(compteDto.getBalance());
            compteCourant.setDecouvert(compteDto.getDecouvert());
            compteCourant.setDevice(compteDto.getDevice());
            compteCourant.setClient(compteOpt.get());
            compteCourant.setNumCompte(generateAccountNumber());
            compteCourant.setStatut(AccountStatus.ACTIVATED);

            this.compteBancaireRepository.save(compteCourant);

        }

        if(clientOpt.isPresent() && (compteDto.getDecouvert() == 0 && compteDto.getTauxInteret() > 0 )){

            CompteEpargne compteEpargne = new CompteEpargne();
            compteEpargne.setCreatdAte(new Date());
            compteEpargne.setBalance(compteDto.getBalance());
            compteEpargne.setTauxInteret(compteDto.getTauxInteret());
            compteEpargne.setDevice(compteDto.getDevice());
            compteEpargne.setClient(compteOpt.get());
            compteEpargne.setNumCompte(generateAccountNumber());
            compteEpargne.setStatut(AccountStatus.ACTIVATED);

            this.compteBancaireRepository.save(compteEpargne);

        }
    }

    @Override
    public List<CompteEpargne> findCompteEpargne() {
        List<CompteEpargne> list = new ArrayList<>();
        for (CompteBancaire c : compteBancaireRepository.findAll()){
            if(c instanceof CompteEpargne){
                list.add((CompteEpargne) c);
            }
        }
        return list;
    }

    @Override
    public List<CompteCourant> findCompteCourant() {
        List<CompteCourant> list = new ArrayList<>();
        for (CompteBancaire c : compteBancaireRepository.findAll()){
            if(c instanceof CompteCourant){
                list.add((CompteCourant) c);
            }
        }
        return list;
    }


    @Override
    public CompteBancaire findOne(String numCompte) {
        return this.compteBancaireRepository.findByNumCompte(numCompte).get();
    }

    @Override
    public boolean activeCompte(String numCompte) {
        return false;
    }

    @Override
    public boolean suspendreCompte(String numCompte) {
        return false;
    }
}
