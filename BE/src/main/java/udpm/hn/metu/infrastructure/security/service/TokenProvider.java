package udpm.hn.metu.infrastructure.security.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import udpm.hn.metu.entity.User;
import udpm.hn.metu.infrastructure.security.repository.SecurityUserRepository;
import udpm.hn.metu.infrastructure.security.response.TokenSubjectResponse;
import udpm.hn.metu.infrastructure.security.user.UserPrincipal;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class TokenProvider {

    @Value("${jwt.secret}")
    private String tokenSecret;

    private final long TOKEN_EXP = System.currentTimeMillis() + 2 * 60 * 60 * 1000;

    @Setter(onMethod_ = @Autowired)
    private SecurityUserRepository userAuthRepository;

    public String createToken(Authentication authentication) throws BadRequestException, JsonProcessingException {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        User user = getCurrentUserLogin(userPrincipal.getEmail());

        if (user == null) throw new BadRequestException("User not found");

        TokenSubjectResponse tokenSubjectResponse = getTokenSubjectResponse(user);
        String subject = new ObjectMapper().writeValueAsString(tokenSubjectResponse);
        Map<String, Object> claims = getBodyClaims(tokenSubjectResponse);

        return Jwts.builder()
                .setSubject(subject)
                .setClaims(claims)
                .setIssuedAt(new java.util.Date(System.currentTimeMillis()))
                .setExpiration(new java.util.Date(TOKEN_EXP))
                .setIssuer("udpm.hn")
                .signWith(Keys.hmacShaKeyFor(tokenSecret.getBytes()))
                .compact();
    }

    public String createToken(String userId) throws BadRequestException, JsonProcessingException {
        User user = userAuthRepository.findById(userId).orElse(null);
        if (user == null) throw new BadRequestException("User not found");

        TokenSubjectResponse tokenSubjectResponse = getTokenSubjectResponse(user);
        String subject = new ObjectMapper().writeValueAsString(tokenSubjectResponse);
        Map<String, Object> claims = getBodyClaims(tokenSubjectResponse);

        return Jwts.builder()
                .setSubject(subject)
                .setClaims(claims)
                .setIssuedAt(new java.util.Date(System.currentTimeMillis()))
                .setExpiration(new java.util.Date(TOKEN_EXP))
                .setIssuer("udpm.hn")
                .signWith(Keys.hmacShaKeyFor(tokenSecret.getBytes()))
                .compact();
    }

    private TokenSubjectResponse getTokenSubjectResponse(User user) {
        TokenSubjectResponse response = new TokenSubjectResponse();
        response.setFullName(user.getFullName());
        response.setUserId(user.getId());
        response.setUserCode(user.getCode());
        response.setPictureUrl(user.getAvatarImage());
        response.setEmail(user.getEmail());
        response.setRolesCode(user.getRole().name());
        response.setRolesName(user.getRole().name());
        return response;
    }

    private static Map<String, Object> getBodyClaims(TokenSubjectResponse tokenSubjectResponse) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("fullName", tokenSubjectResponse.getFullName());
        claims.put("userId", tokenSubjectResponse.getUserId());
        claims.put("userName", tokenSubjectResponse.getUserCode());
        claims.put("userCode", tokenSubjectResponse.getUserCode());
        claims.put("email", tokenSubjectResponse.getEmail());
        claims.put("pictureUrl", tokenSubjectResponse.getPictureUrl());
        claims.put("rolesCode", tokenSubjectResponse.getRolesCode());
        claims.put("rolesName", tokenSubjectResponse.getRolesName());
        claims.put("host", tokenSubjectResponse.getHost());
        return claims;
    }

    public String getUserIdFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(tokenSecret.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody();
        return String.valueOf(claims.get("userId").toString());
    }

    public String getEmailFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(tokenSecret.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody();
        String email = claims.get("email", String.class);
        if (email != null && !email.isEmpty()) {
            return email;
        }
        return claims.get("email", String.class);
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(tokenSecret.getBytes()))
                    .build()
                    .parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }

    private User getCurrentUserLogin(String email) {
        Optional<User> user = userAuthRepository.findByEmail(email);
        return user.orElse(null);
    }

}
