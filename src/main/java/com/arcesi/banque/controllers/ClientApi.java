package com.arcesi.banque.controllers;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.arcesi.banque.request.ClientRequest;
import com.arcesi.banque.response.ClientResponse;

public interface ClientApi {

	
	
	@PostMapping(value ="/api", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ClientResponse> createClient(@RequestBody ClientRequest clientRequest);

	 
	@GetMapping(value = "/api/findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ClientResponse> findOneById(@PathVariable(name = "id") Long id);

	@PostAuthorize("hasAuthority('EMPLOYEE') or hasAuthority('ADMINISTRATEUR')")
	@GetMapping(value ="/api/findByCodeUnique/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ClientResponse> findOneByCodeUniqueClient(@PathVariable(name = "code") String codeUnique);

	@PostAuthorize("hasAuthority('EMPLOYEE') or hasAuthority('ADMINISTRATEUR')")
	@GetMapping(value = "/api/findByName/{nom}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ClientResponse> findOneClientByName(@PathVariable(name = "nom") String nom);


	@PostAuthorize("hasAuthority('EMPLOYEE') or hasAuthority('ADMINISTRATEUR')")
	@GetMapping(value = "/api/findByEmail/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ClientResponse> findClientByEmail(@PathVariable(name = "email") String email);

	@PostAuthorize("hasAuthority('EMPLOYEE') or hasAuthority('ADMINISTRATEUR')")
	@GetMapping(value = "/api/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ClientResponse>> findAllClient(
			@RequestParam(name = "page", defaultValue = "0") final int page,
			@RequestParam(name = "limit", defaultValue = "10") final int limit);

	@PostAuthorize("hasAuthority('EMPLOYEE') or hasAuthority('ADMINISTRATEUR')")
	@PutMapping(value = "/api/{code}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ClientResponse> updateClient(@RequestBody ClientRequest clientRequest,
			@PathVariable(name = "code") Long code);

	@PostAuthorize("hasAuthority('ADMINISTRATEUR')")
	@DeleteMapping(value = "/api/{code}")
	public ResponseEntity<Object> deleteClient(@PathVariable(name = "code") Long code);

	@PostAuthorize("hasAuthority('EMPLOYEE') or hasAuthority('ADMINISTRATEUR')")
	@GetMapping(value="/api/findByNomAndPrenomAndDateOfBirth/{nom}/{prenom}/{dateOfBirth}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ClientResponse> findClientByNomAndPrenomAndDateOfBirth(@PathVariable(name="nom") String nom,@PathVariable(name="prenom")  String prenom,@PathVariable(name="dateOfBirth") @DateTimeFormat(pattern = "yyyy-MM-dd")  LocalDate dateOfBird);

	@GetMapping(value = "/api/findUserConnecte", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ClientResponse> findUserConnecte(Principal principal);
}
