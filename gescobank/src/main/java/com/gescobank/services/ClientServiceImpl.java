package com.gescobank.services;

import com.gescobank.dto.ClientDto;
import com.gescobank.entities.Client;
import com.gescobank.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService{

    //injection de repo
    private final ClientRepository clientRepository;

    ClientServiceImpl (final ClientRepository clientRepository){
     this.clientRepository = clientRepository;
    }

    @Override
    public void createNewClient(ClientDto clientDto) {
        Client client = new Client();
        client.setNom(clientDto.getNom());
        client.setPrenom(clientDto.getPrenom());
        client.setBirthDay(clientDto.getBirthDay());
        client.setEmail(clientDto.getEmail());
        client.setTelephone(clientDto.getTelephone());
        client.setAddress(clientDto.getAddress());

        this.clientRepository.save(client);
    }


    @Override
    public List<Client> findAll() {
        return this.clientRepository.findAll();
    }

    @Override
    public Client findOne(long id) {
        return this.clientRepository.getReferenceById(id );
    }
}
