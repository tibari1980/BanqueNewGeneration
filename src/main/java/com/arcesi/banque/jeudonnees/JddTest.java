package com.arcesi.banque.jeudonnees;

import java.security.SecureRandom;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;


import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.arcesi.banque.entites.AdresseBean;
import com.arcesi.banque.entites.CarteBancaireCompteBean;
import com.arcesi.banque.entites.ClientBean;
import com.arcesi.banque.entites.CompteBean;
import com.arcesi.banque.entites.CompteCourantBean;
import com.arcesi.banque.entites.CompteEpargneBean;
import com.arcesi.banque.entites.EmployeBean;
import com.arcesi.banque.entites.EtablissementBean;
import com.arcesi.banque.entites.GroupBean;
import com.arcesi.banque.entites.OperationBean;
import com.arcesi.banque.entites.RibCompteBean;
import com.arcesi.banque.entites.RoleBean;
import com.arcesi.banque.entites.UserBean;
import com.arcesi.banque.enums.AdresseTypeEnum;
import com.arcesi.banque.enums.AppUserRole;
import com.arcesi.banque.enums.CompteStatusEnumeration;
import com.arcesi.banque.enums.SexEnumeration;
import com.arcesi.banque.enums.StatusCarteEnum;
import com.arcesi.banque.enums.TypeClientEnumeration;
import com.arcesi.banque.enums.TypeOperationEnumeration;
import com.arcesi.banque.repositories.AdresseRepository;
import com.arcesi.banque.repositories.CarteBancaireCompteRepository;
import com.arcesi.banque.repositories.ClientRepository;
import com.arcesi.banque.repositories.CompteRepository;
import com.arcesi.banque.repositories.EmployeRepository;
import com.arcesi.banque.repositories.EtablissementRepository;
import com.arcesi.banque.repositories.GroupRepository;
import com.arcesi.banque.repositories.OperationRepository;
import com.arcesi.banque.repositories.RibCompteRepository;
import com.arcesi.banque.repositories.RoleRepository;
import com.arcesi.banque.repositories.UserRepository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
@AllArgsConstructor
@Transactional
public class JddTest implements CommandLineRunner {

	private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
	private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
	private static final String NUMBER = "0123456789";

	private static final String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER;
	private static SecureRandom random = new SecureRandom();

	private EtablissementRepository etablissementRepository;
	private EmployeRepository employeRepository;
	private ClientRepository clientRepository;
	private CompteRepository compteRepository;
	private OperationRepository operationRepository;
	private RibCompteRepository ribCompteRepository;
	private CarteBancaireCompteRepository carteBancaireCompteRepository;
	private GroupRepository groupRepository;
	private AdresseRepository adresseRepository;
	private RoleRepository roleRepository;
	private UserRepository userRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
   
	@Override
	public void run(String... args) throws Exception {
		employeRepository.deleteAllInBatch();
		groupRepository.deleteAllInBatch();
		// Création de l'établissement La Banque Information;
		EtablissementBean newEtablissement = EtablissementBean.builder().codeEtablissement("10099")
				.createdAt(Instant.now()).updatedAt(null)
				.codeEtablissementUnique(UUID.randomUUID().toString().replace("-", ""))
				.domiciliation("07 RUE PEGUY 75006 PARIS ").emailEtablissement("newbanqueenligne@gmail.com")
				.telephoneEtablissement("0625491640").faxEtablissement("0121201214").codeGuiche("00040")
				.libelleEtablissement("Banque Tibari Nouvelle Génération").build();
		EtablissementBean etablissementSaved = etablissementRepository.save(newEtablissement);
        
		EtablissementBean newEtablissementTest = EtablissementBean.builder().codeEtablissement("10099")
				.createdAt(Instant.now()).updatedAt(null)
				.codeEtablissementUnique(UUID.randomUUID().toString().replace("-", ""))
				.domiciliation("07 RUE PEGUY 75006 PARIS ").emailEtablissement("newbanqueenligne@gmail.com")
				.telephoneEtablissement("0625491640").faxEtablissement("0121201214").codeGuiche("00040")
				.libelleEtablissement("Banque Tibari Nouvelle Génération").build();
		
		EtablissementBean etablissementSavednew = etablissementRepository.save(newEtablissementTest);
		//insertion des groupes
		//ajouter des groupes
		ajouterLesGroupes();
		ajouterLesRoles();
		
		UserBean bean=UserBean.builder()
				.createdAt(Instant.now())
				.updatedAt(null)
				.email("lynakazzar@hotmail.fr")
				.uidUser(UUID.randomUUID().toString().replace("-", ""))
				.firstName("zeroual")
				.lastName("tibari")
				.password(bCryptPasswordEncoder.encode("boudarga"))
				.build();
		  UserBean ub=userRepository.save(bean);
		  UserBean ube=userRepository.findUserBeanByEmail("lynakazzar@hotmail.fr");
		  //RoleBean roleClient=roleRepository.findRoleBeanByRoleName("CLIENT");
		  RoleBean roleEmpl=roleRepository.findRoleBeanByRoleName(AppUserRole.EMPLOYE.getId());
		  RoleBean roleAdmi=roleRepository.findRoleBeanByRoleName(AppUserRole.ADMINISTRATEUR.getId());
		  //ube.getRoleBeans().addAll(Arrays.asList(roleClient,roleEmpl));
		  //ube.getRoleBeans().add(roleClient);
		 
		  //userRepository.saveAndFlush(ube);
		  ube.getRoleBeans().add(roleEmpl);
		   userRepository.saveAndFlush(ube);
		  //ube.getRoleBeans().add(roleAdmi);
		  //userRepository.save(ube);
		  
		  // Insertion des employés de la banque;

		for (int i = 1; i <= 10; i++) {
			@SuppressWarnings("deprecation")

			EmployeBean employeNew = EmployeBean.builder().code(null).createdAt(Instant.now()).updatedAt(null)
					.nomEmploye("Employe" + i).prenomEmploye("Prenom" + i)
					.dateNaissanceEmploye(LocalDate.of(genererInt(1935, 2010), genererInt(1, 10), genererInt(1, 28)))
					.codeEmployeUnique(UUID.randomUUID().toString().replace("-", ""))
					.departementNaissance(String.valueOf(genererInt(1, 99))).emailEmploye("employe" + i + "@gmail.com")
					.telephoneEmploye("06474871" + genererInt(20, 99)).photoEmploye("photoEmploye" + i + ".jpeg")
					.paysNaissance("FRANCE").villeNaissance("PARIS").build();

			 employeNew.setAgeEmploye(Period.between(employeNew.getDateNaissanceEmploye(), LocalDate.now()).getYears());
			
             employeRepository.save(employeNew);
            

		}
		
		
		employeRepository.findAll().forEach(eSup -> {
			EmployeBean empSup1 = employeRepository.findById(new Long(genererInt(1, 10))).get();
			if (empSup1 != null) {
				eSup.setEmployeBeanSup(empSup1);
				employeRepository.saveAndFlush(eSup);
			}
		});
        
		
		// Insertion des clients Pour la banque;

		for (int i = 0; i <= 30; i++) {
			ClientBean clientNew = ClientBean.builder().code(null)
					.createdAt(Instant.now())
					.updatedAt(null)
					.nomClient("Client" + i)
					 .prenomClient("Prenom" + i)
					.dateNaissanceClient(LocalDate.of(genererInt(1935, 2010), genererInt(1, 10), genererInt(1, 28)))
					.codeClientUnique(UUID.randomUUID().toString().replace("-", ""))
					.departementNaissance(String.valueOf(genererInt(1, 99)))
					.emailClient("clientbanque" + i + "@gmail.com")
					.telephoneClient("06254871" + genererInt(20, 99))
					.photoClient("photoClient" + i + ".jpeg")
					.paysNaissance("FRANCE")
					.villeNaissance("PARIS")
					.sex(genererInt(20, 200) % 2== 0? SexEnumeration.HOMME :SexEnumeration.FEMME)
					.typeClient(genererInt(20, 200) % 2== 0 ? TypeClientEnumeration.PHYSIQUE: TypeClientEnumeration.MORALE)
					.build();

			clientNew.setAgeClient(Period.between(clientNew.getDateNaissanceClient(), LocalDate.now()).getYears()); 
			EmployeBean employeBean = employeRepository.findById(new Long(genererInt(1, 10))).get();
			 
			clientRepository.save(clientNew);
		}

		// insertion deux Compte pour toutes les clients

		clientRepository.findAll().forEach(client -> {
			EmployeBean employeBean = employeRepository.findById(new Long(genererInt(1, 10))).get();
			CompteCourantBean cc = CompteCourantBean.builder().createdAt(Instant.now()).updatedAt(null)
					.codeCompteUnique(UUID.randomUUID().toString().replace("-", "")).codeCompte(null)
					.soldeCompte(genererInt(200, 1200)).decouvert(genererInt(1, 10) * 100).clientBean(client)
					.statusCompte(CompteStatusEnumeration.CREATED).employeBean(employeBean)
					.etablissementBean(etablissementSaved).build();

			compteRepository.save(cc);
			client.getCompteBeans().add(cc);
			// on suppose que le compte epargne est crée par un autre employé
			EmployeBean employeBeance = employeRepository.findById(new Long(genererInt(1, 10))).get();

			CompteEpargneBean ce = CompteEpargneBean.builder().createdAt(Instant.now()).updatedAt(null)
					.codeCompteUnique(UUID.randomUUID().toString().replace("-", "")).tauxInteret(5.8)
					.soldeCompte(genererInt(200, 4200)).clientBean(client).statusCompte(CompteStatusEnumeration.CREATED)
					.employeBean(employeBeance).etablissementBean(etablissementSaved).build();
			compteRepository.save(ce);
			client.getCompteBeans().add(ce);
			clientRepository.saveAndFlush(client);

		});

		// insertion de quelque opération pour les comptes
		// existant================================//
		compteRepository.findAll().forEach(cp -> {
			EmployeBean empl = employeRepository.findById(new Long(genererInt(1, 10))).get();
			for (int i = 0; i < 10; i++) {
				OperationBean op = OperationBean.builder().createdAt(Instant.now()).codeOperation(null)
						.codeUniqueOperation(UUID.randomUUID().toString().replace("-", "")).updatedAt(null)
						.compteBean(cp).build();
				double montantOperation = genererInt(2, 10) * 20;
				// définir le type d'operation aleatoire
				if (genererInt(0, 6) % 2 == 0) {
					op.setTypeOperation(TypeOperationEnumeration.DEBUT);
				} else {
					op.setTypeOperation(TypeOperationEnumeration.CREDIT);
				}
				if (op.getTypeOperation().name().equals("DEBUT")) {
					if (cp.getSoldeCompte() > 0 && cp.getSoldeCompte() > montantOperation) {
						cp.setSoldeCompte(cp.getSoldeCompte() - montantOperation);
						compteRepository.save(cp);
						op.setMontantOperation(montantOperation);
						op.setEmployeBean(empl);
						op.setLibelleOperation("Retrait espèce");
						operationRepository.save(op);
						cp.getOperationBeans().add(op);
						compteRepository.saveAndFlush(cp);
					}
				} else {
					cp.setSoldeCompte(cp.getSoldeCompte() + montantOperation);
					compteRepository.save(cp);
					op.setMontantOperation(montantOperation);
					op.setLibelleOperation("Versement espèce");
					op.setEmployeBean(empl);
					operationRepository.save(op);
					cp.getOperationBeans().add(op);
					compteRepository.saveAndFlush(cp);
				}

			}

			CompteBean cp1 = compteRepository.findById(new Long(cp.getCodeCompte())).get();

			// Création d'un rib pour le compte
			RibCompteBean ribCompteBean = RibCompteBean.builder().createdAt(Instant.now()).updatedAt(null).codeRib(null)
					.codeRibUnique(UUID.randomUUID().toString().replace("-", "")).compteBean(cp1)
					.bicCompte(generateRandomString(11)).ibanCompte(construtIban())
					.guichet(etablissementSaved.getCodeGuiche())
					.etablissement(etablissementSaved.getCodeEtablissement()).numeroCompte(genererNumeroCompte())
					.cleRib(String.valueOf(genererInt(10, 99)))
					.domiciliationCompte(etablissementSaved.getDomiciliation()).build();
			ribCompteRepository.save(ribCompteBean);

			CarteBancaireCompteBean carteBancaireCompteBean = CarteBancaireCompteBean.builder().codeCarte(null)
					.codeCarteUnique(UUID.randomUUID().toString().replace("-", ""))
					.codeVerification(String.valueOf(genererInt(100, 999))).numeroCarte(genererNumeroCarteBancaire())
					.dateExperation(LocalDate.now().plusYears(5)).compteBean(cp1).createdAt(Instant.now())
					.statusCarte(StatusCarteEnum.ACTIVATED).updatedAt(null).build();
			// sauvegarder la carte
			carteBancaireCompteRepository.save(carteBancaireCompteBean);

		});
		
		associerEmployeToGroup();
		ajouterDeuxAdressePourChagueEmployeEtClient();
		

	}

	private void ajouterLesRoles() {
		RoleBean roleBean=RoleBean.builder()
				.createdAt(Instant.now())
				.updatedAt(null)
				.codeRole(null)
				.codeUniqueRole(UUID.randomUUID().toString().replace("-", ""))
				.roleName(AppUserRole.CLIENT.getId())
				.build();
		RoleBean roleBean1=RoleBean.builder()
				.createdAt(Instant.now())
				.updatedAt(null)
				.codeRole(null)
				.codeUniqueRole(UUID.randomUUID().toString().replace("-", ""))
				.roleName(AppUserRole.EMPLOYE.getId())
				.build();
		RoleBean roleBean2=RoleBean.builder()
				.createdAt(Instant.now())
				.updatedAt(null)
				.codeRole(null)
				.codeUniqueRole(UUID.randomUUID().toString().replace("-", ""))
				.roleName(AppUserRole.ADMINISTRATEUR.getId())
				.build();
		roleRepository.saveAll(Arrays.asList(roleBean,roleBean1,roleBean2));
		
	}

	private void ajouterDeuxAdressePourChagueEmployeEtClient() {
		//ajouter les adresse pour les employes
		employeRepository.findAll().forEach(em->{
			String[] myVille= {"Paris","Lille","Bordeau","Nimes","Toulouse","Lyon","Nice","Toulon","Marseille"};
			int[] myCodePostal= {75015,22000,30000,45000,55000,87000,14500,54000};
			AdresseBean adresseBean=AdresseBean.builder()
					.createdAt(Instant.now())
					.updatedAt(null)
					.codeUniqueAdresse(UUID.randomUUID().toString().replace("-", ""))
					.nomRue("rue de paris"+genererInt(1, 200))
					.numeroRue(genererInt(1, 150))
					.ville(String.valueOf(myVille[genererInt(0, myVille.length)]))
					.pays("FRANCE")
					.typeAdresse(AdresseTypeEnum.BILLING)
					.codePostale(String.valueOf(genererInt(0, myCodePostal.length)))
					.build();
			EmployeBean emp1=employeRepository.findById(em.getCode()).get();
			adresseBean.setEmployeBean(emp1);
			adresseRepository.save(adresseBean);
			
			AdresseBean adresseShipping=AdresseBean.builder()
					.createdAt(Instant.now())
					.updatedAt(null)
					.codeUniqueAdresse(UUID.randomUUID().toString().replace("-", ""))
					.nomRue("rue de paris"+genererInt(1, 200))
					.numeroRue(genererInt(1, 150))
					.ville(String.valueOf(myVille[genererInt(0, myVille.length)]))
					.pays("FRANCE")
					.typeAdresse(AdresseTypeEnum.SHIPPING)
					.codePostale(String.valueOf(genererInt(0, myCodePostal.length)))
					.build();
			EmployeBean employe=employeRepository.findById(em.getCode()).get();
			adresseShipping.setEmployeBean(employe);
			adresseRepository.save(adresseShipping);
			
			//ajouter les adresse dans l'employer
			em.getAdresseBeans().addAll(Arrays.asList(adresseShipping,adresseBean));
			employeRepository.saveAndFlush(em);
					
		});
		
		//ajouter les adresse pour les clients
				clientRepository.findAll().forEach(cl->{
					String[] myVille= {"Paris","Lille","Bordeau","Nimes","Toulouse","Lyon","Nice","Toulon","Marseille"};
					int[] myCodePostal= {75015,22000,30000,45000,55000,87000,14500,54000};
					AdresseBean adresseBilling=AdresseBean.builder()
							.createdAt(Instant.now())
							.updatedAt(null)
							.codeUniqueAdresse(UUID.randomUUID().toString().replace("-", ""))
							.nomRue("rue de paris"+genererInt(1, 200))
							.numeroRue(genererInt(1, 150))
							.ville(String.valueOf(myVille[genererInt(0, myVille.length)]))
							.pays("FRANCE")
							.typeAdresse(AdresseTypeEnum.BILLING)
							.codePostale(String.valueOf(genererInt(0, myCodePostal.length)))
							.build();
					ClientBean client=clientRepository.findById(cl.getCode()).get();
					adresseBilling.setClientBean(client);
					adresseRepository.save(adresseBilling);
					AdresseBean adresseShipping=AdresseBean.builder()
							.createdAt(Instant.now())
							.updatedAt(null)
							.codeUniqueAdresse(UUID.randomUUID().toString().replace("-", ""))
							.nomRue("rue de paris"+genererInt(1, 200))
							.numeroRue(genererInt(1, 150))
							.ville(String.valueOf(myVille[genererInt(0, myVille.length)]))
							.pays("FRANCE")
							.typeAdresse(AdresseTypeEnum.SHIPPING)
							.codePostale(String.valueOf(genererInt(0, myCodePostal.length)))
							.build();
					ClientBean clientShi=clientRepository.findById(cl.getCode()).get();
					adresseShipping.setClientBean(clientShi);
					adresseRepository.save(adresseShipping);
					cl.getAdresseBeans().addAll(Arrays.asList(adresseBilling,adresseShipping));
					clientRepository.flush();
							
				});
	}

	private void associerEmployeToGroup() {
		//associer employé à un group
		for(int i=1;i<4;i++) {
			EmployeBean em1=employeRepository.findById(new Long(i)).get();
			GroupBean gr=groupRepository.findById(new Long(1L)).get();
			gr.getEmployeBeans().add(em1);
			em1.getGroupBeans().add(gr);
		}
		for(int i=4;i<8;i++) {
			EmployeBean em1=employeRepository.findById(new Long(i)).get();
			GroupBean gr=groupRepository.findById(new Long(2L)).get();
			gr.getEmployeBeans().add(em1);
			em1.getGroupBeans().add(gr);
		}
		for(int i=9;i<=10;i++) {
			EmployeBean em1=employeRepository.findById(new Long(i)).get();
			GroupBean gr=groupRepository.findById(new Long(3L)).get();
			gr.getEmployeBeans().add(em1);
			groupRepository.save(gr);	
		}
	}

	private void ajouterLesGroupes() {
		// Ajouter les groupes
		GroupBean groupBean = GroupBean.builder().codeGroupe(null).createdAt(Instant.now())
				.codeGroupUnique(UUID.randomUUID().toString().replace("-", "")).updatedAt(null)
				.libelleGroup("INFORMATIQUE").build();
		
		this.groupRepository.save(groupBean);

		//em.getGroupBeans().add(groupBean);
		GroupBean groupBean1 = GroupBean.builder().codeGroupe(null).createdAt(Instant.now())
				.codeGroupUnique(UUID.randomUUID().toString().replace("-", "")).updatedAt(null)
				.libelleGroup("RECOUVREMENT ").build();
		this.groupRepository.save(groupBean1);
		
		GroupBean groupBean2 = GroupBean.builder().codeGroupe(null).createdAt(Instant.now())
				.codeGroupUnique(UUID.randomUUID().toString().replace("-", "")).updatedAt(null)
				.libelleGroup("COMMERCIAL").build();
		
		this.groupRepository.save(groupBean2);
		
		GroupBean groupBean3 = GroupBean.builder().codeGroupe(null).createdAt(Instant.now())
				.codeGroupUnique(UUID.randomUUID().toString().replace("-", "")).updatedAt(null)
				.libelleGroup("comptabilité").build();
		groupRepository.save(groupBean3);
		
	}

	
	 
	private String genererNumeroCarteBancaire() {
		// example compte 6223900P020
		StringBuilder st = new StringBuilder();
		st.append(genererInt(1000, 9999));
		st.append(" " + genererInt(1000, 9999));
		st.append(" " + genererInt(1000, 9999));
		st.append(" " + genererInt(1000, 9999));
		return st.toString();
	}

	private String genererNumeroCompte() {
		// example compte 6223900P020
		StringBuilder st = new StringBuilder();
		st.append(genererInt(1000000, 9999999));
		st.append(generateRandomString(1));
		st.append(genererInt(100, 999));
		return st.toString();
	}

	private String construtIban() {
		int number1 = genererInt(10000, 99999);
		int number2 = genererInt(10000, 99999);
		int number3 = genererInt(10000, 99999);
		int number4 = genererInt(10000, 99999);

		StringBuilder st = new StringBuilder();
		st.append("FR");
		st.append(String.valueOf(genererInt(10, 99)));
		st.append(
				String.valueOf(number1) + String.valueOf(number2) + String.valueOf(number3) + String.valueOf(number4));
		st.append(generateRandomString(1));
		st.append(String.valueOf(genererInt(10, 99)));
		return st.toString();
	}

	/**
	 * 
	 * @param borneInf nombre inférieur
	 * @param borneSup nombre supérieur
	 * @return
	 */
	static int genererInt(int borneInf, int borneSup) {
		Random random = new Random();
		int nb;
		nb = borneInf + random.nextInt(borneSup - borneInf);
		return nb;
	}

	public static String generateRandomString(int length) {
		if (length < 1)
			throw new IllegalArgumentException();

		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {

			// 0-62 (exclusive), random returns 0-61
			int rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
			char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);

			sb.append(rndChar);

		}

		return sb.toString();

	}

}
