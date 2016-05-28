package com.madx.auth.filters;

import java.io.IOException;
import java.security.Principal;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;

import com.madx.auth.annotations.Secured;
import com.madx.auth.jwt.RsaKeyProducer;

@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		// Get the HTTP Authorization header from the request
		String authorizationHeader =  requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

		// Check if the HTTP Authorization header is present and formatted correctly 
		if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
			System.out.println("No JWT token !");
			requestContext.setProperty("auth-failed", true);
			requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
			return;
		}

		// Extract the token from the HTTP Authorization header
		String java_web_token = authorizationHeader.substring("Bearer".length()).trim();
		try {
			// Validate the token
			System.out.println("JWT based Auth in action... time to verify th signature");
			System.out.println("JWT being tested:\n" + java_web_token);
			final String username = validateToken(java_web_token);

			final SecurityContext securityContext = requestContext.getSecurityContext();
			if (username != null) {
				requestContext.setSecurityContext(new SecurityContext() {
					@Override
					public Principal getUserPrincipal() {
						return new Principal() {
							@Override
							public String getName() {
								System.out.println("Returning custom Principal - " + username);
								return username;
							}
						};
					}

					@Override
					public boolean isUserInRole(String role) {
						return securityContext.isUserInRole(role);
					}

					@Override
					public boolean isSecure() {
						return securityContext.isSecure();
					}

					@Override
					public String getAuthenticationScheme() {
						return securityContext.getAuthenticationScheme();
					}
				});
			}
		} catch (Exception e) {
			System.out.println("JWT validation failed");
			requestContext.setProperty("auth-failed", true);
			requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
		}
	}

	/**
	 * Check if it was issued by the server and if it's not expired 
	 * @param java_web_token
	 * @throws InvalidJwtException if the token is invalid
	 */
	private String validateToken(String java_web_token) throws InvalidJwtException {
		String username = null;
		RsaJsonWebKey rsaJsonWebKey = RsaKeyProducer.produce();

		System.out.println("RSA hash code... " + rsaJsonWebKey.hashCode());

		JwtConsumer jwtConsumer = new JwtConsumerBuilder()
				.setRequireSubject() // the JWT must have a subject claim
				.setVerificationKey(rsaJsonWebKey.getKey()) // verify the signature with the public key
				.build(); // create the JwtConsumer instance

		try {
			//  Validate the JWT and process it to the Claims
			JwtClaims jwtClaims = jwtConsumer.processToClaims(java_web_token);
			username = (String) jwtClaims.getClaimValue("sub");
			System.out.println("JWT validation succeeded! " + jwtClaims);
		} catch (InvalidJwtException e) {
			e.printStackTrace(); //on purpose
			throw e;
		}
		return username;
	}
}