package org.bgu.security;

import io.jsonwebtoken.Jwts;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bgu.model.BguUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
public class TokenServiceImpl implements TokenService {

	private final Logger logger = LogManager.getLogger(getClass());
	private final KeyStoreService keyService;
	private static final long TOKEN_EXPIRY = 1000 * 60 * 60 * 24 * 10; // 10 days  
	
	@Autowired
	public TokenServiceImpl(final KeyStoreService keyService) {
		this.keyService = keyService;
	}
	
	@Override
	public String createToken(Authentication authentication) {
		BguUser user = (BguUser) authentication;
		final long current = System.currentTimeMillis();
		return Jwts.builder().signWith(keyService.getKeyPair().getPrivate())
				   .setSubject(user.getUsername())
				   .setIssuedAt(new Date(current))
				   .setExpiration(new Date(current + TOKEN_EXPIRY))
				   .compact();
	}

	@Override
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(keyService.getKeyPair().getPublic()).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			logger.error("JWT validation failed at {}. Exception was {}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy")), e.getClass().getName());
			logger.error("Cause was {}. {}", e.getCause(), e.getMessage());
			return false;
		}
	}

	@Override
	public String getUsernameFromToken(String token) {
		return Jwts.parser().setSigningKey(keyService.getKeyPair().getPublic()).parseClaimsJws(token).getBody().getSubject();
	}

}
