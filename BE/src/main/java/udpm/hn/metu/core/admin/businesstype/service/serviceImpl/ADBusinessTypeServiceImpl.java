package udpm.hn.metu.core.admin.businesstype.service.serviceImpl;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import udpm.hn.metu.core.admin.businesstype.model.request.ADBusinessTypeCreateRequest;
import udpm.hn.metu.core.admin.businesstype.model.request.ADBusinessTypeRequest;
import udpm.hn.metu.core.admin.businesstype.repository.ADBusinessTypeRepository;
import udpm.hn.metu.core.admin.businesstype.service.ADBusinessTypeService;
import udpm.hn.metu.core.common.base.PageableObject;
import udpm.hn.metu.core.common.base.ResponseObject;
import udpm.hn.metu.entity.BusinessType;

import java.util.Optional;
import java.util.Random;

@Service
public class ADBusinessTypeServiceImpl implements ADBusinessTypeService {

    @Autowired
    private ADBusinessTypeRepository businessTypeRepository;

    @Override
    public ResponseObject<?> getAllBusinessType(ADBusinessTypeRequest request) {
        Pageable pageable = PageRequest.of(request.getPage() - 1, request.getSize());
        return new ResponseObject<>(
                PageableObject.of(businessTypeRepository.getAllBusinessType(pageable, request)),
                HttpStatus.OK,
                "Lấy danh sách thể loại doanh nghiệp thành công!"
        );
    }

    @Override
    public ResponseObject<?> getBusinessTypeById(String id) {
        return businessTypeRepository.getBusinessTypeById(id)
                .map(subject -> new ResponseObject<>(subject, HttpStatus.OK, "Lấy thông tin thể loại doanh nghiệp thành công"))
                .orElseGet(() -> new ResponseObject<>(null, HttpStatus.NOT_FOUND, id + " không tồn tại"));
    }


    @Override
    public ResponseObject<?> addBusinessType(@Valid ADBusinessTypeCreateRequest request) {
        Random random = new Random();
        int number = random.nextInt(10000);
        String code = String.format("TL%04d", number);
        BusinessType businessType = new BusinessType();
        businessType.setCode(code);
        businessType.setName(request.getName());
        businessType.setDeleted(false);
        businessType.setDescription(request.getDescription());
        BusinessType adBusinessTypeRepository = businessTypeRepository.save(businessType);
        return new ResponseObject<>(null, HttpStatus.CREATED, "Tạo thể loại doanh nghiệp thành công");
    }

    @Override
    public ResponseObject<?> updateBusinessType(String id, ADBusinessTypeCreateRequest request) {
        Optional<BusinessType> businessType = businessTypeRepository.findById(id)
                .map(businessType1 -> {
                    businessType1.setName(request.getName());
                    businessType1.setDescription(request.getDescription());
                    return businessTypeRepository.save(businessType1);
                });
        return businessType
                .map(businessType1 -> new ResponseObject<>(null, HttpStatus.OK, "Cập nhật thể loại doanh nghiệp thành công"))
                .orElseGet(() -> new ResponseObject<>(null, HttpStatus.NOT_FOUND, "Thể loại doanh nghiệp không tồn tại"));
    }

    @Override
    public ResponseObject<?> deleteBusinessType(String id) {
        Optional<BusinessType> optional = businessTypeRepository.findById(id)
                .map(businessType -> {
                    businessType.setDeleted(true);
                    return businessTypeRepository.save(businessType);
                });
        return optional
                .map(businessType -> new ResponseObject<>(null, HttpStatus.OK, "Xóa thể loại doanh nghiệp thành công"))
                .orElseGet(() -> new ResponseObject<>(null, HttpStatus.NOT_FOUND, "Thể loại doanh nghiệp không tồn tại"));
    }

}
