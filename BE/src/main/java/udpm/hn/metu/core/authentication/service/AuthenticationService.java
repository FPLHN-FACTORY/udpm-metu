package udpm.hn.metu.core.authentication.service;

import jakarta.validation.Valid;
import udpm.hn.metu.core.authentication.model.request.AuthLoginRequest;
import udpm.hn.metu.core.authentication.model.request.AuthRefreshRequest;
import udpm.hn.metu.core.authentication.model.request.AuthRegisterRequest;
import udpm.hn.metu.core.common.base.ResponseObject;

public interface AuthenticationService {

    ResponseObject<?> getRefreshToken(@Valid AuthRefreshRequest request);

    ResponseObject<?> logout(@Valid AuthRefreshRequest request);

    ResponseObject<?> login(@Valid AuthLoginRequest request);

    ResponseObject<?> register(@Valid AuthRegisterRequest request);

    ResponseObject<?> getBusinessType();

}
