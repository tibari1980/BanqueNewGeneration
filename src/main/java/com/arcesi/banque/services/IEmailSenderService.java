package com.arcesi.banque.services;

public interface IEmailSenderService {

	void send (final String to,final  String email,final String subject);
}
