package com.gescobank.web;

import com.gescobank.dto.OperationDto;
import com.gescobank.services.OperationService;
import jdk.dynalink.Operation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1")

public class OperationRestController {

    final OperationService operationService;


    OperationRestController(final OperationService operationService) {
        this.operationService = operationService;
    }


    @PostMapping("/operations/versement")
    void effectuerVersement(@RequestBody OperationDto dto)
    {
        this.operationService.effectuerVersement(dto);
    }

    @PostMapping("/operations/retrait")
    void effectuerRetrait(@RequestBody OperationDto dto)
    {
        this.operationService.effectuerRetrait(dto);
    }

    @PostMapping("/operations/virement")
    void virement(@RequestBody OperationDto dto)
    {
        this.operationService.effectuerVirement(dto);
    }

    @GetMapping("/operations/client/{numCompte}")
    List<Operation> findAllOperationByClient(@PathVariable("numCompte") String numCompte)
    {
        return this.operationService.findByClientNumCompte(numCompte);
    }
}