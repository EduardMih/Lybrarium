package com.example.demo.controllers;

import com.example.demo.model.Book;
import com.example.demo.model.Client;
import com.example.demo.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/v1/clients")
public class ClientController {
    @Autowired
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Client>> getClients()
    {
        List<Client> clients = clientService.getAllClients();

        return new ResponseEntity<>(clients, new HttpHeaders(), HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Client> createClient(@RequestBody Client client)
    {
        Client newClient = clientService.addClient(client, client.getName());

        return new ResponseEntity<>(client, new HttpHeaders(), HttpStatus.OK);

    }

    @RequestMapping(path = "/{id}",method = RequestMethod.PUT)
    public ResponseEntity<Client> updateClient(@PathVariable int id, @RequestBody Client client)
    {
        Client updatedClient = clientService.updateClient(id, client.getName());

        return new ResponseEntity<>(updatedClient, new HttpHeaders(), HttpStatus.OK);

    }

    @RequestMapping(path="/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteClient(@PathVariable int id)
    {
        clientService.deleteClient(id);

        return new ResponseEntity<>("Deleted " + id, new HttpHeaders(), HttpStatus.OK);

    }

    @RequestMapping(path = "/search/{searchField}", method = RequestMethod.GET)
    public ResponseEntity<List<Client>> searchBooks(@PathVariable String searchField, @RequestParam(name = "s") String searchString)
    {
        if((!Objects.equals(searchField, "name")) &&
                (!Objects.equals(searchField, "id")))

            return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);

        List<Client> clientList = clientService.searchClient(searchString, searchField);

        return new ResponseEntity<>(clientList, new HttpHeaders(), HttpStatus.OK);


    }

}
