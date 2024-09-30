package udpm.hn.metu.core.authentication.service;

import jakarta.validation.Valid;
import udpm.hn.metu.core.authentication.model.request.AuthRefreshRequest;
import udpm.hn.metu.core.authentication.model.request.AuthUserRequest;
import udpm.hn.metu.core.common.base.ResponseObject;

public interface AuthenticationService {

    ResponseObject<?> getRefreshToken(@Valid AuthRefreshRequest request);

    ResponseObject<?> logout(@Valid AuthRefreshRequest request);

    ResponseObject<?> register(@Valid AuthUserRequest request);

    ResponseObject<?> getBusinessType();

}
