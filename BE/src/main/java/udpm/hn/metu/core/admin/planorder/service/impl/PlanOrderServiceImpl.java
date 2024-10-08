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
import udpm.hn.metu.core.common.base.ResponseObject;
import udpm.hn.metu.entity.*;
import udpm.hn.metu.infrastructure.constant.enums.Status;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanOrderServiceImpl implements PlanOrderService {

    private final PlanOrderExtendRepository planOrderExtendRepository;

    @Override
    public ResponseObject<List<PlanOrderResponse>> getAllPlanOrders(PlanOrderRequest request) {
        Page<Object[]> page = planOrderExtendRepository.getAllPlanOrders(Pageable.unpaged(), request);
        List<PlanOrderResponse> responses = new ArrayList<>();

        for (Object[] obj : page) {
            PlanOrderResponse response = new PlanOrderResponse();
            response.setEmail((String) obj[0]);
            response.setCreateAt((Long) obj[1]);
            response.setExpirationDate((Long) obj[2]);
            response.setPrice((Double) obj[3]);
            response.setQuantity((Short) obj[4]);
            response.setStatus((Status) obj[5]);

            // Thêm business và plan vào danh sách
            response.setBusinesses(Collections.singletonList((Business) obj[6]));
            response.setPlans(Collections.singletonList((Plan) obj[7]));

            responses.add(response);
        }

        return new ResponseObject<>(responses, HttpStatus.OK, "Lấy danh sách kế hoạch và doanh nghiệp thành công");
    }


}
