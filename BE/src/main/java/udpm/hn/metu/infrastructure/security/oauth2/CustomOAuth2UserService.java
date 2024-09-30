package udpm.hn.metu.infrastructure.security.oauth2;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import udpm.hn.metu.entity.User;
import udpm.hn.metu.infrastructure.constant.enums.Role;
import udpm.hn.metu.infrastructure.constant.enums.Status;
import udpm.hn.metu.infrastructure.security.exception.OAuth2AuthenticationProcessingException;
import udpm.hn.metu.infrastructure.security.oauth2.user.OAuth2UserInfo;
import udpm.hn.metu.infrastructure.security.oauth2.user.OAuth2UserInfoFactory;
import udpm.hn.metu.infrastructure.security.repository.UserAuthRepository;
import udpm.hn.metu.infrastructure.security.user.UserPrincipal;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserAuthRepository userAuthRepository;

    private static final Short ACTIVE = 0;

    private static final Short INACTIVE = 1;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
        try {
            return processOAuth2User(oAuth2UserRequest, oAuth2User);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory
                .getOAuth2UserInfo(
                        oAuth2UserRequest.getClientRegistration().getRegistrationId(),
                        oAuth2User.getAttributes()
                );
        if (oAuth2UserInfo.getEmail() == null || oAuth2UserInfo.getEmail().isBlank()) {
            throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
        }

        Optional<User> userAuthOptional = userAuthRepository.findByEmail(oAuth2UserInfo.getEmail());

        if (userAuthOptional.isPresent()) {
            User user = userAuthOptional.get();
            User userExist = (User) updateExistingUser(user, oAuth2UserInfo);
            return UserPrincipal.create(userExist, oAuth2User.getAttributes(), userExist.getRole().name());
        }

        Object newUser = registerNewUser(oAuth2UserRequest, oAuth2UserInfo);
        if (newUser instanceof User originUser) {
            return UserPrincipal.create(originUser, oAuth2User.getAttributes(), Role.MANAGER.name());
        } else {
            throw new OAuth2AuthenticationProcessingException("Invalid email format");
        }
    }

    private Object registerNewUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {
        String email = oAuth2UserInfo.getEmail();
        if (email.matches(".*[a-zA-Z]+\\d{5}@fpt\\.edu\\.vn$")) {
            if (email.matches("p[a-zA-Z]\\d{5}@fpt\\.edu\\.vn$")) {
                throw new OAuth2AuthenticationProcessingException("Invalid email format");
            }
        } else if (email.endsWith("@fpt.edu.vn") || email.endsWith("@fe.edu.vn") || email.endsWith("@gmail.com")) {
            User user = new User();
            user.setEmail(email);
            user.setCode(email.substring(0, email.indexOf("@")));
            user.setAvatarImage(oAuth2UserInfo.getImageUrl());
            user.setFullName(oAuth2UserInfo.getName());
            user.setStatus(Status.ACTIVE);
            user.setRole(Role.MANAGER);
            return userAuthRepository.save(user);
        } else {
            throw new OAuth2AuthenticationProcessingException("Invalid email format");
        }

        return null;
    }

    private Object updateExistingUser(User existingUser, OAuth2UserInfo oAuth2UserInfo) {
        existingUser.setFullName(oAuth2UserInfo.getName());
        existingUser.setAvatarImage(oAuth2UserInfo.getImageUrl());
        if (existingUser.getStatus() == null) existingUser.setStatus(Status.ACTIVE);
        return userAuthRepository.save(existingUser);
    }

}
