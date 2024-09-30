package udpm.hn.metu.core.authentication.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import udpm.hn.metu.core.authentication.model.request.AuthRefreshRequest;
import udpm.hn.metu.core.authentication.model.request.AuthUserRequest;
import udpm.hn.metu.core.authentication.service.AuthenticationService;
import udpm.hn.metu.infrastructure.constant.MappingConstant;
import udpm.hn.metu.utils.Helper;

@RestController
@RequestMapping(MappingConstant.API_AUTH_PREFIX)
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody AuthRefreshRequest request) throws BadRequestException, JsonProcessingException {
        return Helper.createResponseEntity(authenticationService.getRefreshToken(request));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody AuthRefreshRequest request) {
        return Helper.createResponseEntity(authenticationService.logout(request));
    }

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody AuthUserRequest request) {
        return Helper.createResponseEntity(authenticationService.register(request));
    }

    @GetMapping("/get-business-type")
    public ResponseEntity<?> getListBusiness() {
        return Helper.createResponseEntity(authenticationService.getBusinessType());
    }


}
