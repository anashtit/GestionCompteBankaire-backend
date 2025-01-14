package com.gescobank.services;

import com.gescobank.entities.Client;
import com.gescobank.dto.ClientDto;

import java.util.List;

public interface ClientService {

    void createNewClient(ClientDto clientDto);

    List<Client> findAll();

    Client findOne(long id);


}
