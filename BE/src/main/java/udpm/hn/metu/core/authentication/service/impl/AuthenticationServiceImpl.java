package udpm.hn.metu.core.authentication.service.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import udpm.hn.metu.core.authentication.model.request.AuthLoginRequest;
import udpm.hn.metu.core.authentication.model.request.AuthRefreshRequest;
import udpm.hn.metu.core.authentication.model.request.AuthRegisterRequest;
import udpm.hn.metu.core.authentication.model.response.AuthRefreshResponse;
import udpm.hn.metu.core.authentication.repository.AuthBusinessRepository;
import udpm.hn.metu.core.authentication.repository.AuthBusinessTypeRepository;
import udpm.hn.metu.core.authentication.repository.AuthRefreshTokenExtendRepository;
import udpm.hn.metu.core.authentication.repository.AuthUserRepository;
import udpm.hn.metu.core.authentication.service.AuthenticationService;
import udpm.hn.metu.core.common.base.ResponseObject;
import udpm.hn.metu.entity.Business;
import udpm.hn.metu.entity.BusinessType;
import udpm.hn.metu.entity.RefreshToken;
import udpm.hn.metu.entity.User;
import udpm.hn.metu.infrastructure.constant.enums.Role;
import udpm.hn.metu.infrastructure.constant.enums.Status;
import udpm.hn.metu.infrastructure.security.service.TokenProvider;
import udpm.hn.metu.utils.AESPasswordCryptoUtil;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Validated
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private final TokenProvider tokenProvider;

    private final AuthRefreshTokenExtendRepository authRefreshTokenExtendRepository;

    private final AuthUserRepository authUserRepository;

    private final AuthBusinessRepository authBusinessRepository;

    private final AuthBusinessTypeRepository authBusinessTypeRepository;

    @Override
    public ResponseObject<?> getRefreshToken(@Valid AuthRefreshRequest request) {
        try {
            String refreshToken = request.getRefreshToken();

            Optional<RefreshToken> refreshTokenOptional = authRefreshTokenExtendRepository.findByRefreshToken(refreshToken);
            if (refreshTokenOptional.isEmpty()) {
                return ResponseObject.errorForward("Refresh token not found", HttpStatus.NOT_FOUND);
            }

            RefreshToken refreshTokenEntity = refreshTokenOptional.get();
            if (refreshTokenEntity.getRevokedAt() != null) {
                return ResponseObject.errorForward("Refresh token has been revoked", HttpStatus.BAD_REQUEST);
            }

            String accessToken = tokenProvider.createToken(refreshTokenEntity.getUserId());
            return ResponseObject.successForward(new AuthRefreshResponse(accessToken, refreshToken), "Get refresh token successfully");
        } catch (Exception e) {
            return ResponseObject.errorForward("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseObject<?> logout(@Valid AuthRefreshRequest request) {
        String refreshToken = request.getRefreshToken();

        Optional<RefreshToken> refreshTokenOptional = authRefreshTokenExtendRepository.findByRefreshToken(refreshToken);
        if (refreshTokenOptional.isEmpty()) {
            return ResponseObject.errorForward("Refresh token not found", HttpStatus.NOT_FOUND);
        }

        RefreshToken refreshTokenEntity = refreshTokenOptional.get();
        refreshTokenEntity.setRevokedAt(System.currentTimeMillis());
        authRefreshTokenExtendRepository.save(refreshTokenEntity);

        return ResponseObject.successForward(null, "Logout successfully");
    }

    @Override
    public ResponseObject<?> login(AuthLoginRequest request) {
        try {
            Optional<User> userOptional = authUserRepository.findByEmail(request.getEmail());
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                String encodedPassword = AESPasswordCryptoUtil.encrypt(request.getPassword());
                if (encodedPassword.matches(userOptional.get().getPassword())) {
                    return ResponseObject.successForward(user, "Login Success");
                } else {
                    return ResponseObject.errorForward("Incorrect password", HttpStatus.BAD_REQUEST);
                }
            }
            return ResponseObject.errorForward("User does not exits", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.info("😢😢 ~> Error encrypt login");
            return ResponseObject.errorForward(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseObject<?> register(AuthRegisterRequest request) {
        try {
            String email = request.getEmail();
            Optional<BusinessType> businessTypeOptional = authBusinessTypeRepository.findById(request.getBusinessTypeId());
            if (businessTypeOptional.isPresent()) {
                Business business = new Business();
                business.setBusinessType(businessTypeOptional.get());
                business.setName(request.getBusinessName());
                business.setStatus(Status.ACTIVE);
                business.setCode(email.substring(0, email.indexOf("@")));
                business.setDescription("Business Typing");
                Business newBusiness = authBusinessRepository.save(business);
                User user = new User();
                user.setBusiness(newBusiness);
                user.setEmail(email);
                String encodedPassword = AESPasswordCryptoUtil.encrypt(request.getPassword());
                user.setPassword(encodedPassword);
                user.setRole(Role.MANAGER);
                user.setFullName(request.getFullName());
                user.setCode(email.substring(0, email.indexOf("@")));
                user.setStatus(Status.ACTIVE);
                authUserRepository.save(user);
                return ResponseObject.successForward(user, "Register Success");
            }
            return ResponseObject.errorForward("Business Type is empty", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.info("😢😢 ~> Error encrypt register");
            return ResponseObject.errorForward(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseObject<?> getBusinessType() {
        return ResponseObject.successForward(authBusinessTypeRepository.findAll(), "Get list business type success");
    }

}
