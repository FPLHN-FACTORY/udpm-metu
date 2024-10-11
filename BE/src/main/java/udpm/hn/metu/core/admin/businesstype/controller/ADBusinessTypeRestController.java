package udpm.hn.metu.core.admin.businesstype.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import udpm.hn.metu.core.admin.businesstype.model.request.ADBusinessTypeCreateRequest;
import udpm.hn.metu.core.admin.businesstype.model.request.ADBusinessTypeRequest;
import udpm.hn.metu.core.admin.businesstype.service.ADBusinessTypeService;

import udpm.hn.metu.infrastructure.constant.MappingConstant;
import udpm.hn.metu.utils.Helper;

import java.util.List;

@RequestMapping(MappingConstant.API_ADMIN_BUSINESS_TYPE)
@RestController
public class ADBusinessTypeRestController {

    @Autowired
    private ADBusinessTypeService businessTypeService;

    @GetMapping("")
    public ResponseEntity<?> getAllBusinessType(final ADBusinessTypeRequest request) {
        return Helper.createResponseEntity(businessTypeService.getAllBusinessType(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBusinessTypeById(@PathVariable("id") String id) {
        return Helper.createResponseEntity(businessTypeService.getBusinessTypeById(id));
    }

    @PostMapping("")
    public ResponseEntity<?> addBusinessType(@Valid @RequestBody ADBusinessTypeCreateRequest request) {
        return Helper.createResponseEntity(businessTypeService.addBusinessType(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBusinessType(@Valid @RequestBody ADBusinessTypeCreateRequest request, @PathVariable("id") String id) {
        return Helper.createResponseEntity(businessTypeService.updateBusinessType(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeBusinessType(@PathVariable("id") String id) {
        return Helper.createResponseEntity(businessTypeService.deleteBusinessType(id));
    }
}
