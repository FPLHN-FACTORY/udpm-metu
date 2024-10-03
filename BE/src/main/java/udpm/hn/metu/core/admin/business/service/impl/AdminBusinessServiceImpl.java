package udpm.hn.metu.core.admin.business.service.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import udpm.hn.metu.core.admin.business.model.request.AdminBusinessRequest;
import udpm.hn.metu.core.admin.business.repository.AdminBusinessRepository;
import udpm.hn.metu.core.admin.business.service.AdminBusinessService;
import udpm.hn.metu.core.common.base.PageableObject;
import udpm.hn.metu.core.common.base.ResponseObject;
import udpm.hn.metu.entity.Business;
import udpm.hn.metu.utils.Helper;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminBusinessServiceImpl implements AdminBusinessService {

    private final AdminBusinessRepository businessRepository;

    @Override
    public ResponseObject<?> getAllBusiness(AdminBusinessRequest request) {
        Pageable pageable = Helper.createPageable(request,"createAt");
        return new ResponseObject<>(
                PageableObject.of(businessRepository.getAllBusiness(pageable,request)), HttpStatus.OK,
                "GetAll business successfully"
        );
    }
    @Override
    public ResponseObject<?> updateBusiness(String businessId,@Valid AdminBusinessRequest request) {

        String name = request.getName().trim();
        Optional<Business> businessRequest = businessRepository.findById(businessId);

        if (name.isEmpty()) {
            return ResponseObject.errorForward("Tên doanh nghiệp không được để trống", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Business business = businessRequest.get();
        business.setCode(request.getCode());
        business.setName(request.getName());
        businessRepository.save(business);
        return  ResponseObject.successForward("null", "Cập nhật thành công");

    }
    @Override
    public ResponseObject<?> deleteBusiness(String id) {
        Optional<Business> optionalBusiness = businessRepository.findById(id);
        if (optionalBusiness.isEmpty()) {
                return  ResponseObject.errorForward("Không tìm thấy doanh nghiệp", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            Business business = optionalBusiness.get();
            business.setDeleted(true);
            businessRepository.save(business);
            return  ResponseObject.successForward("null", "Xóa thành công");
    }
}
