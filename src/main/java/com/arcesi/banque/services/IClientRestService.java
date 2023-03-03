package com.arcesi.banque.services;

import java.time.LocalDate;
import java.util.List;

import com.arcesi.banque.dto.ClientDTO;

public interface IClientRestService {

	public ClientDTO saveClient(ClientDTO clientDTO);

	public ClientDTO findById(final Long id);

	public ClientDTO updateClient(final Long id, ClientDTO clientDTO);

	public void deleteClient(final Long id);

	public ClientDTO findClientBeanByCodeUnique(final String code);

	public ClientDTO findClientBeanByNom(final String nom);

	public List<ClientDTO> findAllClients(final int page, final int limit);

	public ClientDTO findClientBeanByEmail(String email);

	public ClientDTO findClientBeanByNameAndPrenomAndDateNaissance(final String nom, final  String prenom,final LocalDate dateOfBird);
}
