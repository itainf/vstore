package com.vstore.boot.autoconfigure.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.Iterator;

public class AccessDecisionManager implements org.springframework.security.access.AccessDecisionManager {

	private static final Logger logger = LoggerFactory.getLogger(AccessDecisionManager.class);

	@Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {

		Assert.notNull(object, " object is must not null !");

		if (configAttributes == null) {
			return;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("Check authority {} " , object.toString());
			logger.debug("This Object need authority is {} , authority is {}" , configAttributes,authentication.getAuthorities());

		}


		for(ConfigAttribute ca :configAttributes){
			String needAuthority =  ca.getAttribute();
			for (GrantedAuthority ga : authentication.getAuthorities()) {
				if (needAuthority.equals(ga.getAuthority())) {
					return;
				}
			}
		 }



		throw new AccessDeniedException(
				"Access is denied on:["+
				object.toString()+"]\n This Object need authority is："+
				configAttributes+ "\n authority is："+
				authentication.getAuthorities());
	}

	@Override
    public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	@Override
    public boolean supports(Class<?> clazz) {
		return true;
	}
}
