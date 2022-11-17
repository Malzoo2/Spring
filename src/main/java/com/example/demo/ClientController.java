package com.example.demo;

import org.springframework.data.repository.support.Repositories;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

import com.example.demo.Entity.Client;
import com.example.demo.Repository.ClientRepository;

@RestController
@RequestMapping("/clients")
public class ClientController {

	private final ClientRepository clientRepository;

	public ClientController(ClientRepository clientRepository) {
		super();
		this.clientRepository = clientRepository;
	}
	
	@GetMapping
	public List<Client> getClients(){
		System.out.println("all clients");
		return clientRepository.findAll();
	}
	
	
	@GetMapping("/{id}")
	public Client getClient(@PathVariable Long id)
	{
//		return clientRepository.getById(id);
		return clientRepository.findById(id).orElseThrow(RuntimeException::new);
	}
	
	
	@PostMapping(path = "clients", 
	        consumes = MediaType.APPLICATION_JSON_VALUE, 
	        produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Client> createClient (@RequestBody Client client) throws URISyntaxException {
		System.out.println("create client");
		Client savedClient  = clientRepository.save(client);
		return ResponseEntity.created(new URI("/clients"+savedClient.getId())).body(savedClient);
	}
//	
	@PutMapping("/{id}")
	public ResponseEntity<Client> updateClient (@PathVariable Long id, @RequestBody Client client){
		Client currentClient = clientRepository.findById(id).orElseThrow(RuntimeException::new);
		currentClient.setEmail(client.getEmail());
		currentClient.setName(client.getName());
		currentClient = clientRepository.save(client);
		return ResponseEntity.ok(currentClient);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Client> deleteClient(@PathVariable Long id){
		clientRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
}
