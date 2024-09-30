package udpm.hn.metu.core.authentication.service.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import udpm.hn.metu.core.authentication.model.request.AuthRefreshRequest;
import udpm.hn.metu.core.authentication.model.request.AuthUserRequest;
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

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Validated
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
    public ResponseObject<?> register(AuthUserRequest request) {
        Business business = new Business();
        business.setName(request.getBusinessName());
        Optional<BusinessType> businessTypeOptional = authBusinessTypeRepository.findById(request.getBusinessTypeId());
        if (businessTypeOptional.isPresent()) {
            business.setBusinessType(businessTypeOptional.get());
            User user = new User();
            String email = request.getEmail();
            user.setBusiness(business);
            user.setEmail(email);
            user.setPassword(request.getPassword());
            user.setRole(Role.MANAGER);
            user.setFullName(request.getFullName());
            user.setCode(email.substring(0, email.indexOf("@")));
            user.setStatus(Status.ACTIVE); // ACTIVE = 0 - INACTIVE = 1
            authUserRepository.save(user);
            return ResponseObject.successForward(user, "Register Success");
        }
        return ResponseObject.errorForward("Business Type is empty", HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseObject<?> getBusinessType() {
        return ResponseObject.successForward(authBusinessTypeRepository.findAll(), "Get list business type success");
    }

}
