package com.gescobank.web;

import ch.qos.logback.core.net.server.Client;
import com.gescobank.dto.ClientDto;
import com.gescobank.services.ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class ClientRestController {

private final ClientService clientService;


    ClientRestController (final ClientService clientService){
    this.clientService = clientService;
}

@PostMapping ( "/clients")
    void createClient(@RequestBody ClientDto dto)
{
    this.clientService.createNewClient(dto);
}


@GetMapping( "/clients")
    List<Client> findAll()
   {
    return  this.clientService.findAll();
   }


   @GetMapping( "/clients/{id}")
    Client findOne(@PathVariable("id") Long id )
    {
        return  this.clientService.findOne(id);
    }
}
