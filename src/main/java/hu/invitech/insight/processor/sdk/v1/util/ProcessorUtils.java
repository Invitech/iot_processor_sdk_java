package hu.invitech.insight.processor.sdk.v1.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * Common helper methods for implementing a processor.
 *
 * @since version 1
 * @author kavaleczm
 * @version 1
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class ProcessorUtils {
    /**
     * <p>
     * Helper method to validate the JWT bearer token according to specification.
     * </p>
     *
     * @param token the JWT bearer token to validate
     *
     * @return the {@link io.jsonwebtoken.Claims} of the token.
     *
     * @throws io.jsonwebtoken.JwtException if the token is not valid according to the specification.
     * @see hu.invitech.insight.processor.sdk.v1.server.ServerInfo#token
     * @since version 1
     */
    public static Claims validateToken(String token) throws JwtException {
        Claims claims = Jwts.parser().requireSubject("processor").setSigningKey(getPublicKey()).parseClaimsJws(token).getBody();
        String baseUrl = claims.getIssuer();
        Integer processorId = claims.get("processorId", Integer.class);
        if (baseUrl.isEmpty() || processorId == null) {
            throw new JwtException("Required data missing from token.");
        }
        return claims;
    }

    /**
     * <p>
     * Helper method to get the {@link java.security.PublicKey} instance needed to validate the JWT bearer token.
     * </p>
     *
     * @return he {@link PublicKey} instance needed to validate the JWT token, or null if an error occured while converting the key.
     *
     * @since version 1
     */
    public static PublicKey getPublicKey() {
        try {
            return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.getDecoder().decode(ApiConstants.JWT_PUBLIC_KEY)));
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }
}
