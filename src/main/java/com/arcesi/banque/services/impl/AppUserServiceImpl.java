package com.arcesi.banque.services.impl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arcesi.banque.dto.RoleDTO;
import com.arcesi.banque.dto.UserDTO;
import com.arcesi.banque.entites.ConfirmationToken;
import com.arcesi.banque.entites.UserBean;
import com.arcesi.banque.exceptions.EntityNotFoundException;
import com.arcesi.banque.exceptions.ErrorsCodes;
import com.arcesi.banque.exceptions.exceptionsMail.ApiRequestException;
import com.arcesi.banque.exceptions.exceptionsMail.EnumExceptionMessage;
import com.arcesi.banque.repositories.UserRepository;
import com.arcesi.banque.request.utilRequestUser.RegistrationRequest;
import com.arcesi.banque.services.AppUserService;
import com.arcesi.banque.services.IConfirmationTokenService;
import com.arcesi.banque.services.IEmailSenderService;
import com.arcesi.banque.utils.ControleSyntaxe;
import com.arcesi.banque.utils.EmailValidator;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class AppUserServiceImpl implements AppUserService {
	
	private EmailValidator emailValidator;
	private UserRepository userRepository;
	private IConfirmationTokenService confirmationTokenService;
	private IEmailSenderService emailSenderService;
	
	@Override
	public String register(RegistrationRequest registrationRequest) {
		log.info("Inside méthode register in service AppUserServiceImpl {}  ",registrationRequest.getEmail());
	    	boolean isEmailValide=emailValidator.test(registrationRequest.getEmail());
	    	if(!isEmailValide) {
	    		throw new ApiRequestException(EnumExceptionMessage.EMAIL_NOT_VALID.getCode());
	    	}
	    	UserBean appUser=new UserBean();
	    	BeanUtils.copyProperties(registrationRequest, appUser);
	    	
	    	boolean isUserExist=userRepository.findByEmail(appUser.getEmail()).isPresent();
	    	if(isUserExist) {
	    		throw new ApiRequestException(EnumExceptionMessage.USER_FOUNT_IN_OUR_DATA_BASE.getCode());
	    	}
	    	 
	    	appUser.setPassword(null);
	    	appUser.setCreatedAt(Instant.now());
	    	try {
				InetAddress IP=InetAddress.getLocalHost();
				appUser.setIpAdresse(IP.getHostAddress());
			} catch (UnknownHostException e) {
				log.error("Erreur lors de la récupération de l'adresse ip :", e.getMessage());
			}
	    	appUser.setUidUser(UUID.randomUUID().toString());
	    	
	    	//sauvegarder le user dans la base de données 
	    	userRepository.save(appUser);
	    	
	    	//Send confirmation token
	    	String token=UUID.randomUUID().toString();
			ConfirmationToken confirmation=new ConfirmationToken(token, LocalDateTime.now(),LocalDateTime.now().plusMinutes(10), appUser);
			confirmationTokenService.saveConfirmationToken(confirmation);
	    	
			//todo send email 
			String link="http://localhost:8080/mabanque/v1/users/api/confirm?token="+token;
	    	String nomClient=ControleSyntaxe.mettrePremierLetteEnMajuscule(registrationRequest.getFirstName());
			emailSenderService.send(registrationRequest.getEmail(), buildEmail(nomClient,link ),EnumExceptionMessage.COMPTE_CREE_SUCCESS.getCode());
		return null;
	}

	@Override
	public boolean enableAppUser(String email) {
		log.info("Inside méthode enableUser in Service AppUserService : {}",email);
		UserBean isUserExist=userRepository.findUserBeanByEmail(email);
		if(null==isUserExist) {
			log.error("user not found");
			throw new ApiRequestException(EnumExceptionMessage.USER_ENABLED_NOT_FOUND.getCode());
		}
		isUserExist.setUpdatedAt(Instant.now());
		isUserExist.setEnabled(Boolean.TRUE);
	    UserBean userUpdated=userRepository.saveAndFlush(isUserExist);
		Boolean IsUpdated=Boolean.FALSE;
	    if(userUpdated==null) {
	    	IsUpdated=Boolean.FALSE;
	    }else {
	    	IsUpdated=Boolean.TRUE;
	    }
		return IsUpdated;
	}
	
	

	@Override
	public UserDTO saveUser(UserDTO userDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO updateUser(Long id, UserDTO userDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserDTO loadUserByemail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoleDTO saveRole(RoleDTO roleDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoleDTO findRoleBeanById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoleDTO updateRole(Long id, RoleDTO roleDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addRoleToUser(String email, String roleName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteRole(Long id) {
		// TODO Auto-generated method stub
		
	}

	
	
  //***************************************** Build email *****************************//
	private String buildEmail(String name, String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Activation du compte</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Bonjour " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Vous venez de créer votre compte sur notre portail BANQUE EN LIGNE TIBARI.  Pour l'activer, veuillez cliquer le lien suivant: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Activer maintenant</a> </p></blockquote>\n A noter : si vous ne validez pas votre compte sous 7 jours, ce dernier sera automatiquement supprimé. <p>Cordialement,<br> BANQUE EN LIGNE.<br> Mr Zeroual Tibari</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }

	@Override
	public UserDTO loadUserByUserName(String email) {
		log.info("Inside method loadUserByUserName in AppUserServiceImp : {} ",email);
		UserBean bean=userRepository.findUserBeanByEmail(email);
		if (null == bean) {
			log.error("User {} : not found in our db");
			throw new EntityNotFoundException("User avec l'email  : " + email + " est introuvable dans notre base de données.",
					ErrorsCodes.UTILISATEUR_NOT_FOUND);
		}

		return  UserDTO.toEntity(bean);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserBean user=userRepository.findUserBeanByEmail(email);
		if(user==null) throw new UsernameNotFoundException(email);
		Collection<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
		user.getRoleBeans().forEach(r->{
			authorities.add(new SimpleGrantedAuthority(r.getRoleName()));
		});
		return new User(user.getEmail(), user.getPassword(),authorities);
	}
 
}
