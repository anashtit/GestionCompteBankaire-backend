package com.gescobank.web;

import ch.qos.logback.core.net.server.Client;
import com.gescobank.dto.ClientDto;
import com.gescobank.entities.CompteBancaire;
import com.gescobank.entities.CompteCourant;
import com.gescobank.entities.CompteEpargne;
import com.gescobank.services.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")

public class CompteBancaireRestController {

    private final CompteService compteService;


    CompteBancaireRestController (final CompteService compteService){
        this.compteService = CompteService;
    }



    @PostMapping( "/comptes")
    void createAccount(@RequestBody CompteDto compteDto)
    {
        this.compteService.createAccount(compteDto);
    }



    @GetMapping( "/comptes/type/{type}")
    List<?> findAll(@PathVariable ("type") String type){
        if(type.equals("CC"))
            this.compteService.findComptesCourant();
        if(type.equals("CE"))
           return this.compteService.findComptesEpargne();
        return null;
    }


    @GetMapping( "/comptes/type/{type}")
    ResponseEntity<?> findCompte(@PathVariable ("numCompte") String numCompte ,
                                 @PathVariable ("type") String type){
        CompteBancaire compteBancaire = this.compteService.findOne(numCompte);
        if(type.equals("CC") && (compteBancaire instanceof CompteCourant))
           return ResponseEntity.ok(( CompteCourant)compteBancaire);
        if(type.equals("CE") && (compteBancaire instanceof CompteEpargne))
            return ResponseEntity.ok(( CompteEpargne)compteBancaire);
        return null;
    }


    @GetMapping( "/comptes/active/{numCompte}")
    boolean activeCompte (@PathVariable ("numCompte") String numCompte){
        return this.compteService.activeCompte(numCompte);
    }


    @GetMapping( "/comptes/suspendre/{numCompte}")
    boolean suspendreCompte (@PathVariable ("numCompte") String numCompte){
        return this.compteService.suspendCompte(numCompte);
    }
}
