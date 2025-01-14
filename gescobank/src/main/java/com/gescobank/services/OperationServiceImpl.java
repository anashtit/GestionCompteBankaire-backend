package com.gescobank.services;

import com.gescobank.dto.OperationDto;
import com.gescobank.entities.CompteBancaire;
import com.gescobank.entities.Operation;
import com.gescobank.enums.AccountStatus;
import com.gescobank.enums.TypeOperation;
import com.gescobank.repositories.CompteBancaireRepository;
import com.gescobank.repositories.OperationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OperationServiceImpl implements OperationService {

    private final CompteBancaireRepository compteBancaireRepository;
    private final OperationRepository operationRepository;

    public OperationServiceImpl(
            final CompteBancaireRepository compteBancaireRepository,
            final OperationRepository operationRepository
    ) {
        this.compteBancaireRepository = compteBancaireRepository;
        this.operationRepository = operationRepository;
    }

    @Override
    public CompteBancaire effectuerVersement(OperationDto dto) {
        Optional<CompteBancaire> compteOpt = this.compteBancaireRepository.findByNumCompte(dto.getNumCompte());
        if (compteOpt.isPresent()) {
            CompteBancaire compte = compteOpt.get();
            if (compte.getStatut().equals(AccountStatus.ACTIVATED)) {
                compte.setBalance(compte.getBalance() + dto.getAmount());

                Operation operation = new Operation();
                operation.setDateOperation(new Date());
                operation.setAmount(dto.getAmount());
                operation.setCompte(compte);
                operation.setTypeOperation(TypeOperation.CREDIT);
                operation.setNumOperation(generateAccountNumber());

                this.compteBancaireRepository.save(compte);
                this.operationRepository.save(operation);
                return compte;
            } else {
                throw new RuntimeException("L'opération est impossible : le compte est suspendu.");
            }
        } else {
            throw new RuntimeException("Ce compte n'existe pas.");
        }
    }

    @Override
    public CompteBancaire effectuerRetrait(OperationDto dto) {
        Optional<CompteBancaire> compteOpt = this.compteBancaireRepository.findByNumCompte(dto.getNumCompte());
        if (compteOpt.isPresent()) {
            CompteBancaire compte = compteOpt.get();
            if (compte.getStatut().equals(AccountStatus.ACTIVATED) && compte.getBalance() >= dto.getAmount()) {
                compte.setBalance(compte.getBalance() - dto.getAmount());
                compte = this.compteBancaireRepository.save(compte);

                Operation operation = new Operation();
                operation.setDateOperation(new Date());
                operation.setAmount(dto.getAmount());
                operation.setCompte(compte);
                operation.setTypeOperation(TypeOperation.DEBIT);
                operation.setNumOperation(generateAccountNumber());

                this.operationRepository.save(operation);
                return compte;
            } else {
                throw new RuntimeException("Solde insuffisant ou compte inactif.");
            }
        } else {
            throw new RuntimeException("Ce compte n'existe pas.");
        }
    }

    @Override
    public boolean effectuerVirement(OperationDto dto) {
        String numCompteSource = dto.getNumCompteSource();
        String numCompteDestination = dto.getNumCompteDestination();

        OperationDto dtoSource = new OperationDto(numCompteSource, null, dto.getAmount());
        CompteBancaire compteSource = effectuerRetrait(dtoSource);

        if (compteSource != null) {
            OperationDto dtoDestination = new OperationDto(null, numCompteDestination, dto.getAmount());
            effectuerVersement(dtoDestination);
            return true;
        }
        return false;
    }

    @Override
    public List<Operation> findByClientNumCompte(String numCompte) {
        List<Operation> list = new ArrayList<>();
        for (Operation o : this.operationRepository.findAll()) {
            if (o.getCompte().getNumCompte().equals(numCompte)) {
                list.add(o);
            }
        }
        return list;
    }

    private String generateAccountNumber() {
        return "OP" + System.currentTimeMillis(); // Exemple de génération de numéro unique
    }
}
