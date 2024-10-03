package udpm.hn.metu.core.admin.business.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import udpm.hn.metu.core.admin.business.model.request.AdminBusinessRequest;
import udpm.hn.metu.core.admin.business.service.impl.AdminBusinessServiceImpl;
import udpm.hn.metu.utils.Helper;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping("/business")
public class AdminBusinessController {


    private final AdminBusinessServiceImpl businessService;

    @GetMapping
    public ResponseEntity<?> getAllBusiness(AdminBusinessRequest request) {
        return Helper.createResponseEntity(businessService.getAllBusiness(request));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBusiness(@PathVariable String id,@RequestBody AdminBusinessRequest request) {
        return Helper.createResponseEntity(businessService.updateBusiness(id,request));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBusiness(@PathVariable String id) {
        return Helper.createResponseEntity(businessService.deleteBusiness(id));
    }



}
