package com.arcesi.banque.services.impl;

import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arcesi.banque.dto.AdresseDTO;
import com.arcesi.banque.dto.ClientDTO;
import com.arcesi.banque.dto.UserDTO;
import com.arcesi.banque.entites.ClientBean;
import com.arcesi.banque.entites.RoleBean;
import com.arcesi.banque.entites.UserBean;
import com.arcesi.banque.enums.AppUserRole;
import com.arcesi.banque.exceptions.EntityNotFoundException;
import com.arcesi.banque.exceptions.ErrorsCodes;
import com.arcesi.banque.exceptions.InvalidEntityException;
import com.arcesi.banque.exceptions.MessageErrorsConstants;
import com.arcesi.banque.repositories.ClientRepository;
import com.arcesi.banque.repositories.RoleRepository;
import com.arcesi.banque.repositories.UserRepository;
import com.arcesi.banque.request.utilRequestUser.RegistrationRequest;
import com.arcesi.banque.services.AppUserService;
import com.arcesi.banque.services.IClientRestService;
import com.arcesi.banque.validators.ClientValidators;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
@Getter @Setter
@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class ClientRestServiceImpl implements IClientRestService{

	private ClientRepository clientRepository;
	private AppUserService appUserService;
	private RoleRepository roleRepository;
	private UserRepository userRepository;
	
	 
	
	@Override
	public ClientDTO saveClient(ClientDTO clientDTO) {
		log.info("Inside method SaveClient in Service ClientRestServiceImp :", clientDTO);
		List<String> errors = ClientValidators.validate(clientDTO);
		if (!errors.isEmpty()) {
			log.error("Client is not valid");
			throw new InvalidEntityException(MessageErrorsConstants.CLIENT_INVALID.getCode(), ErrorsCodes.CLIENT_NOT_VALID, errors);
		}
		clientDTO.setCreatedAt(Instant.now());
		clientDTO.setCodeClientUnique(UUID.randomUUID().toString().replace("-", ""));
		if (CollectionUtils.isNotEmpty(clientDTO.getAdresseDTOs())) {
			for (int i = 0; i < clientDTO.getAdresseDTOs().size(); i++) {
				AdresseDTO adresseDto = clientDTO.getAdresseDTOs().get(i);
				adresseDto.setCreatedAt(Instant.now());
				adresseDto.setCodeUniqueAdresse(UUID.randomUUID().toString().replace("-", ""));
				adresseDto.setClientDTO(clientDTO);
				clientDTO.getAdresseDTOs().set(i, adresseDto);

			}
		}
		//vérifié si le client existe 
		ClientBean checkClientExist=clientRepository.findClientBeanByEmailClientIgnoreCase(clientDTO.getEmailClient());
		if(null!=checkClientExist) {
            log.info("Client already exist !!");
            throw new InvalidEntityException("Le Client avec l'email "+clientDTO.getEmailClient() +" exist .",ErrorsCodes.CLIENT_EMAIL_EXIST,null);
		}
		//Vérifié si le client à un compte utilisateur
		UserBean checkUserExiste=userRepository.findUserBeanByEmail(clientDTO.getEmailClient());
		
		if(null!=checkUserExiste) {
			log.info("Client existe dans notre base utilisateur !!");
			throw new InvalidEntityException(MessageErrorsConstants.USER_ALREADY_EXIST.getCode());
		}
		ClientBean clientBean;
		try {
			clientBean = clientRepository.save(ClientDTO.fromEntity(clientDTO));
		} catch (Exception e) {
			throw new InvalidEntityException(e.getMessage());
		}
		log.info("Client : {} created successfully!!!", clientBean);
		
		//Création d'un compte pour le client (User)
		//Chercher Le role
		RoleBean role=roleRepository.findRoleBeanByRoleName(AppUserRole.CLIENT.getId());
		Set<RoleBean> roleBeans=new HashSet<RoleBean>();
		roleBeans.add(role);
		RegistrationRequest request=RegistrationRequest.builder()
				.email(clientBean.getEmailClient())
				.password(null)
				.lastName(clientBean.getNomClient())
				.firstName(clientBean.getPrenomClient())
				.build();
		
		request.setRoleBeans(roleBeans);		
		appUserService.register(request);
		return ClientDTO.toEntity(clientBean);
	}

	@Override
	public ClientDTO findById(Long id) {
		log.info("Inside method findById in Service ClientRestServiceImp : code client {}", id);
		ClientBean clientBean = clientRepository.findClientBeanByCode(id);
		if (null == clientBean) {
			log.error("Client not found in our data base : {}", clientBean);
			throw new EntityNotFoundException("Client avec  Id : " + id + " est introuvable dans notre base de données", ErrorsCodes.CLIENT_NOT_FOUND);
		}
		return ClientDTO.toEntity(clientBean);
	}

	@Override
	public ClientDTO updateClient(Long id, ClientDTO clientDTO) {
		log.info("Inside method updateClient in service ClientRestServiceImp : {}", clientDTO);
		List<String> errors = ClientValidators.validate(clientDTO);
		if (!errors.isEmpty()) {
			log.error("Client is not valid : {} ", clientDTO);
			throw new InvalidEntityException("Le client est invalide ", ErrorsCodes.CLIENT_NOT_VALID, errors);
		}
		ClientBean beanInOurDB = new ClientBean();
		ClientBean bean = new ClientBean();
//		
			beanInOurDB = clientRepository.findById(id).orElse(null);
			if(beanInOurDB==null) {
				log.error("Client not found in our base de données :");
				throw new EntityNotFoundException("Le client est introuvable dans la base de données ",ErrorsCodes.CLIENT_NOT_FOUND);
			}
			clientDTO.setCreatedAt(beanInOurDB.getCreatedAt());
			clientDTO.setCode(beanInOurDB.getCode());
			clientDTO.setCodeClientUnique(beanInOurDB.getCodeClientUnique());
			clientDTO.setUpdatedAt(Instant.now());
			
			if(CollectionUtils.isNotEmpty(beanInOurDB.getAdresseBeans())) {
				for(int i=0;i<beanInOurDB.getAdresseBeans().size();i++) {
					AdresseDTO adresseDto = clientDTO.getAdresseDTOs().get(i);
					adresseDto.setCodeAdresse(beanInOurDB .getAdresseBeans().get(i).getCodeAdresse());
					adresseDto.setCreatedAt(beanInOurDB.getAdresseBeans().get(i).getCreatedAt());
					adresseDto.setUpdatedAt(Instant.now());
					adresseDto.setCodeUniqueAdresse(beanInOurDB .getAdresseBeans().get(i).getCodeUniqueAdresse());
					adresseDto.setClientDTO(clientDTO);
					clientDTO.getAdresseDTOs().set(i, adresseDto);
				}
			}
			
			bean = clientRepository.save(ClientDTO.fromEntity(clientDTO));
			if(null==bean) {
				log.error("Client not valide");
				throw new InvalidEntityException("Erreur lors de l'enregistrement du client Veuillez vérifier les données : {}  "+ clientDTO.toString()+" .");
			}
//		} catch (Exception e) {
//			throw new EntityNotFoundException("Client est introuvable dans la bdd", e.getCause());
//		}
		log.info("Client : {}  updated successfully !!", bean);
		return ClientDTO.toEntity(bean);
		
	}

	@Override
	public void deleteClient(Long id) {
		log.info("Inside method deleteClient in ClientRestServiceImp : {}", id);
		ClientBean bean;
		try {
			bean = clientRepository.findById(id).get();
		} catch (Exception e) {
			log.error("Client Not found in our data base {}", id);
			throw new EntityNotFoundException("Client avec le code : " + id + " est introuvable dans la bdd",
					ErrorsCodes.CLIENT_NOT_FOUND);
		}

		clientRepository.delete(bean);
		log.info("Client with id : {} deleted successufully !!", id);

	}

	@Override
	public ClientDTO findClientBeanByCodeUnique(String code) {
		log.info("Inside method findClientBeanByCodeUnique in ClientRestServiceImp : {} ", code);
		ClientBean bean = clientRepository.findByCodeClientUnique(code);
		if (null == bean) {
			log.error("Client Not Found in our data base try again");
			throw new EntityNotFoundException(
					"Client avec le code unique  : " + code + " est introuvable dans notre base de données.",
					ErrorsCodes.CLIENT_NOT_FOUND);
		}

		return ClientDTO.toEntity(bean);
	}

	@Override
	public ClientDTO findClientBeanByNom(String nom) {
		log.info("Inside method findClientBeanByCodeUnique in ClientRestServiceImp : {} ", nom);
		ClientBean bean = clientRepository.findClientBeanByNomClient(nom);
		if (null == bean) {
			log.error("Client Not Found in our data base try again");
			throw new EntityNotFoundException("Client avec  : " + nom + " est introuvable dans notre base de données.",
					ErrorsCodes.CLIENT_NOT_FOUND);
		}

		return ClientDTO.toEntity(bean);
	}

	 

	@Override
	public List<ClientDTO> findAllClients(int page, int limit) {
		if (page > 0) {
			page = page - 1;
		}
		Pageable pageable = PageRequest.of(page, limit, Sort.by("code").descending());
		Page<ClientBean> pageclientBean = clientRepository.findAll(pageable);
		List<ClientBean> listeClientBeans = pageclientBean.getContent();
		if (listeClientBeans.isEmpty()) {
			log.error("List of clients is empty :{}", listeClientBeans);
			throw new EntityNotFoundException("aucun clients trouvés dans notre base de données",
					ErrorsCodes.CLIENT_NOT_FOUND);
		}
		List<ClientDTO> clientsDtos = listeClientBeans.stream().map(ClientDTO::toEntity).collect(Collectors.toList());
		return clientsDtos;
	}

	@Override
	public ClientDTO findClientBeanByEmail(String email) {
		log.info("Inside method findClientBeanByEmail in ClientRestServiceImp : {} ",email);
		ClientBean bean = clientRepository.findClientBeanByEmailClientIgnoreCase(email);
		if (null == bean) {
			log.error("Client Not Found by email  in our data base try again");
			throw new EntityNotFoundException("Client avec l'email  : " + email + " est introuvable dans notre base de données.",
					ErrorsCodes.CLIENT_NOT_FOUND);
		}

		return ClientDTO.toEntity(bean);
	}

	@Override
	public ClientDTO findClientBeanByNameAndPrenomAndDateNaissance(String nom, String prenom, LocalDate dateOfBird) {
		log.info("Inside method findClientBeanByNameAndPrenomAndDateNaissance in ClientRestServiceImp : {} ", nom,prenom,dateOfBird);
		ClientBean bean = clientRepository.findClientBeanByNomClientAndPrenomClientAndDateNaissanceClient(nom,prenom,dateOfBird);
		if (null == bean) {
			log.error("Client Not Found in our data base try again");
			throw new EntityNotFoundException("Client avec  : Nom : " + nom  + "  -   Prénomm : " +  prenom +" -  Date de naissance : " + dateOfBird + " est introuvable dans notre base de données.",
					ErrorsCodes.CLIENT_NOT_FOUND);
		}

		return ClientDTO.toEntity(bean);
	}

}
