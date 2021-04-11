package com.example.demo.services;

import com.example.demo.model.Client;
import com.example.demo.model.ComicBook;
import com.example.demo.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class ClientService {
    @Autowired
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAllClients()
    {
        List<Client> clients = new ArrayList<>();

        clients = clientRepository.findAll();

        Collections.sort(clients);

        return clients;

    }

    public Client addClient(Client client, String name)
    {
        client.setName(name);
        client = clientRepository.save(client);

        return client;

    }

    public Client updateClient(int id, String name)
    {
        Client updatedClient = clientRepository.getOne(id);

        updatedClient.setName(name);
        updatedClient = clientRepository.save(updatedClient);

        return updatedClient;

    }

    public void deleteClient(int id)
    {
        clientRepository.deleteById(id);
    }

    public List<Client> searchClient(String searchString, String field)
    {
        if (Objects.equals(field, "name"))
        {

            return clientRepository.findByNameContainsIgnoreCase(searchString);

        }

        return clientRepository.findById(Integer.parseInt(searchString));

    }
}
