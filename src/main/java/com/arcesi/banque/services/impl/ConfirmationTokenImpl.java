package com.arcesi.banque.services.impl;

import java.time.Instant;
import java.time.LocalDateTime;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zeroual tibari
 * ingénieur de developpement
 */
import com.arcesi.banque.entites.ConfirmationToken;
import com.arcesi.banque.entites.UserBean;
import com.arcesi.banque.exceptions.exceptionsMail.ApiRequestException;
import com.arcesi.banque.exceptions.exceptionsMail.EnumExceptionMessage;
import com.arcesi.banque.repositories.ConfirmationTokenRepository;
import com.arcesi.banque.repositories.UserRepository;
import com.arcesi.banque.services.AppUserService;
import com.arcesi.banque.services.IConfirmationTokenService;
import com.arcesi.banque.services.IEmailSenderService;
import com.arcesi.banque.utils.ControleSyntaxe;
import com.arcesi.banque.utils.GenerateurPassword;
import com.arcesi.banque.utils.TemplateEmail;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional

@Slf4j
public class ConfirmationTokenImpl implements IConfirmationTokenService {

	
	private final ConfirmationTokenRepository confirmationTokenRepository;
	private final IConfirmationTokenService confirmationTokenService;
	private final AppUserService appUserService;
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder  bCryptPasswordEncoder;
	private IEmailSenderService emailSenderService;
	

	public ConfirmationTokenImpl(@Lazy ConfirmationTokenRepository confirmationTokenRepository,
			@Lazy IConfirmationTokenService confirmationTokenService,@Lazy AppUserService appUserService,
			@Lazy BCryptPasswordEncoder bCryptPasswordEncoder,
			UserRepository userRepository,
			IEmailSenderService emailSenderService) {
		super();
		this.confirmationTokenRepository = confirmationTokenRepository;
		this.confirmationTokenService = confirmationTokenService;
		this.appUserService = appUserService;
		this.bCryptPasswordEncoder=bCryptPasswordEncoder;
		this.userRepository=userRepository;
		this.emailSenderService=emailSenderService;
	}

	@Override
	public ConfirmationToken saveConfirmationToken(ConfirmationToken token) {
		log.info("Inside méthode saveConfirmationToken in ConfirmationTokenImpl : {} ", token);
		return confirmationTokenRepository.save(token);
	}

	@Override
	public String confirmToken(String token) {
		ConfirmationToken confirmationToken = confirmationTokenRepository.getToken(token);
		if (confirmationToken == null) {
			throw new ApiRequestException(EnumExceptionMessage.TOKEN_NOT_FOUND.getCode());
		}
		if (confirmationToken.getConfirmedAt() != null) {
			throw new ApiRequestException(EnumExceptionMessage.EMAIL_ALREADY_CONFIRMED.getCode());
		}
		LocalDateTime experidAt = confirmationToken.getExperidAt();
		if (experidAt.isBefore(LocalDateTime.now())) {
			throw new ApiRequestException(EnumExceptionMessage.TOKEN_EXPIRED_TIME.getCode());
		}

		 LocalDateTime confirmedAt=LocalDateTime.now();
		 confirmationTokenRepository.updateConfirmedAt(token,confirmedAt);
		 
		 //activer le compte utilisateur 
		Boolean isEnabled= appUserService.enableAppUser(confirmationToken.getAppUser().getEmail());
		if(BooleanUtils.isTrue(isEnabled)) {
			String password=GenerateurPassword.generPassWord();
			confirmationToken.getAppUser().setPassword(bCryptPasswordEncoder.encode(password));
			confirmationToken.getAppUser().setUpdatedAt(Instant.now());
			userRepository.save(confirmationToken.getAppUser());
	    	String nomClient=ControleSyntaxe.mettrePremierLetteEnMajuscule(confirmationToken.getAppUser().getFirstName());
	    	String link="http://localhost:8080/mabanque/v1/users/api/confirm?token="+null;
			emailSenderService.send(confirmationToken.getAppUser().getEmail(),TemplateEmail.buildEmailVerificationOK(nomClient, password,link,confirmationToken.getAppUser().getEmail()),EnumExceptionMessage.COMPTE_VALIDATED_SUCCESSFULLY.getCode());
			
			
		}
		
		 return "confirmed";
	}

	private String buildEmail(String nomClient, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConfirmationToken findConfirmationTokenByToken(String token) {
		if(StringUtils.isEmpty(token)) {
			throw new ApiRequestException(EnumExceptionMessage.TOKEN_VIDE.getCode());
		}
		
		return confirmationTokenRepository.findConfirmationtokenByToken(token);
	}

}
