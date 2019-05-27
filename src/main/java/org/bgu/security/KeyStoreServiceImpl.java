package org.bgu.security;

import org.bgu.config.properties.KeyStoreProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;

@Service
public class KeyStoreServiceImpl implements KeyStoreService {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final KeyPair keyPair;
	
	@Autowired
	public KeyStoreServiceImpl(KeyStoreProperties keyStoreProperties) {
		try {
			KeyStore keyStore = KeyStore.getInstance(keyStoreProperties.getType());
			keyStore.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(keyStoreProperties.getFileName()), keyStoreProperties.getPassword());
			Key key = keyStore.getKey(keyStoreProperties.getAlias(), keyStoreProperties.getPassword());
			if (key instanceof PrivateKey) {
				// Get the certificate
				Certificate cert = keyStore.getCertificate(keyStoreProperties.getAlias());
				
				// Get Public Key
				PublicKey pubKey = cert.getPublicKey();
				this.keyPair = new KeyPair(pubKey, (PrivateKey) key);
			} else {
				throw new IOException("Failed to read KeyStore");
			}
		} catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException | UnrecoverableKeyException e) {
			logger.error("Failed to generate KeyPair: {}", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public KeyPair getKeyPair() {
		return this.keyPair;
	}
}
