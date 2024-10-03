package udpm.hn.metu.core.manager.example.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import udpm.hn.metu.core.common.base.ResponseObject;
import udpm.hn.metu.core.manager.example.repository.ExampleUserRepository;
import udpm.hn.metu.infrastructure.constant.MappingConstant;
import udpm.hn.metu.utils.Helper;

@RestController
@RequestMapping(MappingConstant.API_MANAGER_FEATURE)
@RequiredArgsConstructor
public class ExampleController {

    private static final Logger log = LoggerFactory.getLogger(ExampleController.class);
    private final ExampleUserRepository exampleUserRepository;

    @GetMapping()
    public ResponseEntity<?> getUser() {
        return Helper.createResponseEntity(ResponseObject.successForward(exampleUserRepository.getListUser(),
                "Lấy data user thành công"));
    }

}
