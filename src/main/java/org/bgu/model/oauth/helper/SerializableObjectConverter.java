package org.bgu.model.oauth.helper;

import org.apache.commons.codec.binary.Base64;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.util.SerializationUtils;

public class SerializableObjectConverter {

	public static String serialize(OAuth2Authentication object) {
        try {
            byte[] bytes = SerializationUtils.serialize(object);
            return Base64.encodeBase64String(bytes);
        } catch(Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static OAuth2Authentication deserialize(String encodedObject) {
        try {
            byte[] bytes = Base64.decodeBase64(encodedObject);
            return (OAuth2Authentication) SerializationUtils.deserialize(bytes);
        } catch(Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
