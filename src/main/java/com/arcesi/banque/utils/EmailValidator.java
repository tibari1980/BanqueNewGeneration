package com.arcesi.banque.utils;
/**
 * @author Mr Zeroual tibari
 * ingénieur de développement
 */
import java.util.function.Predicate;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.arcesi.banque.exceptions.excep.CannotBeNullException;
@Service
public class EmailValidator  implements Predicate<String>{

	
	// private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$";
		private static final String EMAIL_PATTERN = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";
	
	@Override
	public boolean test(String email) {
		if (StringUtils.isEmpty(email)) {
			throw new CannotBeNullException("ne doit pas être null");
		}
		return Pattern.matches(EMAIL_PATTERN,email);

	}
}
