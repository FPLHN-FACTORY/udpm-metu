package udpm.hn.metu.core.admin.category.controiler;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import udpm.hn.metu.core.admin.category.service.AdCategoryService;
import udpm.hn.metu.infrastructure.constant.MappingConstant;
import udpm.hn.metu.utils.Helper;

@RestController
@RequestMapping(MappingConstant.API_ADMIN_CATEGORY)
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AdCategoryRest {

    private final AdCategoryService adCategoryService;

    @GetMapping("")
    public ResponseEntity<?> viewCategory(){
        return Helper.createResponseEntity(adCategoryService.allCategory());
    }
}
