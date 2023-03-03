package com.arcesi.banque.controllers.imp;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arcesi.banque.controllers.ClientApi;
import com.arcesi.banque.dto.ClientDTO;
import com.arcesi.banque.exceptions.excep.CannotBeNullException;
import com.arcesi.banque.request.ClientRequest;
import com.arcesi.banque.response.ClientResponse;
import com.arcesi.banque.services.IClientRestService;
import com.arcesi.banque.utils.Constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@RestController
@Getter
@Setter
@Slf4j
@AllArgsConstructor
@RequestMapping(value = Constants.APP_ROOT + "/clients")
public class ClientRestApi implements ClientApi {

	private final IClientRestService iClientrestService;

	
	@Override
	public ResponseEntity<ClientResponse> createClient(ClientRequest clientRequest) {
		
		String nome=Principal.class.getName();
		log.info("Inside method createClient in controller ClientRestApi : client info : {} ", clientRequest);
		ClientDTO clientDTO = ClientDTO.clientRequestToClientDto(clientRequest);
		ClientDTO clientSaved = iClientrestService.saveClient(clientDTO);
		ClientResponse clientResponse = ClientDTO.toClientResponse(clientSaved);
		return new ResponseEntity<ClientResponse>(clientResponse, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<ClientResponse> findOneById(Long id) {
		log.info("Inside method findOneById in controllers ClientRestApi : {} ", id);
		if (null == id) {
			log.error("Id is not valide try agair : {}", id);
			throw new CannotBeNullException("L'identifiant ne doit pas être null :");
		}
		ClientDTO clientDTO = iClientrestService.findById(id);
		return new ResponseEntity<ClientResponse>(ClientDTO.toClientResponse(clientDTO), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ClientResponse> findOneByCodeUniqueClient(String codeUnique) {
		log.info("Inside method findOneByCodeUnique in ClientRestApi : {} ", codeUnique);

		if (StringUtils.isBlank(codeUnique)) {
			log.error("Code Unique is not valid try again : {} ", codeUnique);
			return null;
		}
		ClientDTO clientDTO = iClientrestService.findClientBeanByCodeUnique(codeUnique);
		return new ResponseEntity<ClientResponse>(ClientDTO.toClientResponse(clientDTO), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ClientResponse> findOneClientByName(String nom) {
		log.info("Inside méthod findOneClientByName in controllers ClientRestApi : {}", nom);
		if (StringUtils.isBlank(nom)) {
			log.error("Name is not valid try again", nom);
			throw new CannotBeNullException("Le nom ne peut pas être null!!!");
		}
		ClientDTO clientDTO = iClientrestService.findClientBeanByNom(nom);
		return new ResponseEntity<ClientResponse>(ClientDTO.toClientResponse(clientDTO), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ClientResponse> findClientByEmail(String email) {
		log.info("Inside methode findClientByEmail in controllers ClientRestApi : {} ", email);
		if (StringUtils.isBlank(email)) {
			log.error("L'email is not valid try again :{}", email);
			throw new CannotBeNullException("L'adresse email est vide ");
		}
		ClientDTO clientDto = iClientrestService.findClientBeanByEmail(email);

		return new ResponseEntity<ClientResponse>(ClientDTO.toClientResponse(clientDto), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<ClientResponse>> findAllClient(int page, int limit) {
		log.info("Inside method findallClient in controllers ClientRestApi : page :{} size : {}", page, limit);
		List<ClientDTO> clientDtos = iClientrestService.findAllClients(page, limit);
		List<ClientResponse> clientResponses = clientDtos.stream().filter(a -> a != null)
				.map(ClientDTO::toClientResponse).collect(Collectors.toList());
		return new ResponseEntity<List<ClientResponse>>(clientResponses, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ClientResponse> updateClient(ClientRequest clientRequest, Long code) {
		log.info("Inside method updateClient in controllers ClientRestApi : client {} code : {}", clientRequest, code);
		if (code == null) {
			log.info("code Client is null", code);
			throw new CannotBeNullException("code ne peut pas être null");
		}
		ClientDTO clientDTO = iClientrestService.updateClient(code, ClientDTO.clientRequestToClientDto(clientRequest));
		return new ResponseEntity<ClientResponse>(ClientDTO.toClientResponse(clientDTO), HttpStatus.ACCEPTED);
	}

	@Override
	public ResponseEntity<Object> deleteClient(Long code) {
		log.info("Inside method deleteClient in controllers ClientsRestApi : {}", code);
		if (null == code) {
			log.error("code is not valid try again :{}", code);
			throw new CannotBeNullException("code is not valid (isEmpty");
		}
		iClientrestService.deleteClient(code);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}

	@Override
	public ResponseEntity<ClientResponse> findClientByNomAndPrenomAndDateOfBirth(String nom, String prenom,
			LocalDate dateOfBird) {
		log.info("Inside methode findClientByNomAndPrenomAndDateOfBirth in controllers ClientRestApi : {} ,  {} , {} ",
				nom, prenom, dateOfBird);
		if (StringUtils.isBlank(nom) || StringUtils.isBlank(prenom) || null == dateOfBird) {
			log.error("FirstName or LastName or DateOfBirth  is not valid try again :{}", nom, prenom, dateOfBird);
			throw new CannotBeNullException("Le nom et le prénom et la date de naissance sont obligatoire !  ");
		}
		ClientDTO clientDto = iClientrestService.findClientBeanByNameAndPrenomAndDateNaissance(nom, prenom, dateOfBird);

		return new ResponseEntity<ClientResponse>(ClientDTO.toClientResponse(clientDto), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ClientResponse> findUserConnecte(Principal principal) {
		String email=principal.getName();
		if(StringUtils.isNotEmpty(email)) {
			System.out.println("Bravo user !!!!!!!!");
		}
		ClientDTO clientDto = iClientrestService.findClientBeanByEmail(email);
		return new ResponseEntity<ClientResponse>(ClientDTO.toClientResponse(clientDto),HttpStatus.OK);
	}

}
