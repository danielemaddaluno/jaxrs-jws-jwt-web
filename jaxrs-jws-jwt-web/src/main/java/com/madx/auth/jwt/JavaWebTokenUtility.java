package com.madx.auth.jwt;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.lang.JoseException;
import com.madx.auth.filters.AuthenticationFilter;

public class JavaWebTokenUtility {

    public static String buildJWT(String subject) {
        RsaJsonWebKey rsaJsonWebKey = RsaKeyProducer.produce();
        System.out.println("RSA hash code... " + rsaJsonWebKey.hashCode());

        JwtClaims claims = new JwtClaims();
        claims.setSubject(subject); // the subject/principal is whom the token is about
        claims.setIssuer("https://github.com/danielemaddaluno");
        claims.setExpirationTimeMinutesInTheFuture(24 * 60);

        JsonWebSignature jws = new JsonWebSignature();
        jws.setPayload(claims.toJson());
        jws.setKey(rsaJsonWebKey.getPrivateKey());
        jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);

        String jwt = null;
        try {
            jwt = jws.getCompactSerialization();
        } catch (JoseException ex) {
            Logger.getLogger(AuthenticationFilter.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Claim:\n" + claims);
        System.out.println("JWS:\n" + jws);
        System.out.println("JWT:\n" + jwt);

        return jwt;
    }
}
