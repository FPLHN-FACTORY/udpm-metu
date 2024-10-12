package udpm.hn.metu.core.admin.planorder.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import udpm.hn.metu.core.admin.planorder.model.request.PlanOrderRequest;
import udpm.hn.metu.core.admin.planorder.model.response.PlanOrderResponse;
import udpm.hn.metu.core.admin.planorder.repository.PlanOrderExtendRepository;
import udpm.hn.metu.core.admin.planorder.service.PlanOrderService;
import udpm.hn.metu.core.common.base.PageableObject;
import udpm.hn.metu.core.common.base.ResponseObject;
import udpm.hn.metu.entity.*;
import udpm.hn.metu.infrastructure.constant.enums.Status;
import udpm.hn.metu.utils.Helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanOrderServiceImpl implements PlanOrderService {

    private final PlanOrderExtendRepository planOrderExtendRepository;

    @Override
    public ResponseObject<?> getAllPlanOrders(PlanOrderRequest request) {
        // Tạo đối tượng Pageable dựa trên các tham số trong request (có thể sắp xếp theo ngày tạo hoặc một cột khác)
        Pageable pageable = Helper.createPageable(request);

        return new ResponseObject<>(
                PageableObject.of(planOrderExtendRepository.getAllPlanOrders(pageable, request)),
                HttpStatus.OK,
                "Get all plan order successfully"
        );
    }
}

