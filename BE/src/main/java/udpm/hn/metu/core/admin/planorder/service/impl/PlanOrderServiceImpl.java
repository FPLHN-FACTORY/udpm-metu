package udpm.hn.metu.core.admin.planorder.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import udpm.hn.metu.core.admin.planorder.model.request.CreateUpdatePlanOrderRequest;
import udpm.hn.metu.core.admin.planorder.model.request.PlanOrderRequest;
import udpm.hn.metu.core.admin.planorder.model.response.PlanOrderResponse;
import udpm.hn.metu.core.admin.planorder.repository.BusinessExtendRepository;
import udpm.hn.metu.core.admin.planorder.repository.PlanExtendRepository;
import udpm.hn.metu.core.admin.planorder.repository.PlanOrderExtendRepository;
import udpm.hn.metu.core.admin.planorder.service.PlanOrderService;
import udpm.hn.metu.core.common.base.PageableObject;
import udpm.hn.metu.core.common.base.ResponseObject;
import udpm.hn.metu.core.manager.createmenu.model.response.MWidgetResponse;
import udpm.hn.metu.entity.*;
import udpm.hn.metu.utils.Helper;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanOrderServiceImpl implements PlanOrderService {

    private final PlanOrderExtendRepository planOrderExtendRepository;

    @Override
    public ResponseObject<List<PlanOrderResponse>> getAllPlanOrders() {
        List<PlanOrderResponse> responses = new ArrayList<>();

        List<PlanOrder> planOrders = planOrderExtendRepository.findAll();

        if (planOrders.isEmpty()) {
            return new ResponseObject<>(null, HttpStatus.BAD_REQUEST, "Lấy danh sách PlanOrder thất bại");
        }

        for (PlanOrder planOrder : planOrders) {
            PlanOrderResponse planOrderResponse = new PlanOrderResponse();

            // Gán thông tin từ PlanOrder vào response
            planOrderResponse.setEmail(planOrder.getEmail());
            planOrderResponse.setCreateAt(planOrder.getCreateAt());
            planOrderResponse.setExpDate(planOrder.getExpirationDate());
            planOrderResponse.setPrice(planOrder.getPrice());
            planOrderResponse.setQuantity(planOrder.getQuantity());
            planOrderResponse.setStatus(planOrder.getStatus());

            // Lấy thông tin từ khóa ngoại Plan
            Plan plan = planOrder.getPlan();
            List<Plan> plans = new ArrayList<>();
            plans.add(plan);
            planOrderResponse.setPlans(plans);

            // Lấy thông tin từ khóa ngoại Business
            Business business = planOrder.getBusiness();
            List<Business> businesses = new ArrayList<>();
            businesses.add(business);
            planOrderResponse.setBusinesses(businesses);

            responses.add(planOrderResponse);
        }

        return new ResponseObject<>(responses, HttpStatus.OK, "Lấy danh sách kế hoạch và doanh nghiệp thành công");

    }

    @Override
    public ResponseObject<?> createPlanOrder(CreateUpdatePlanOrderRequest request) {
        return null;
    }

    @Override
    public ResponseObject<?> updatePlanOrder(String planOrderId, CreateUpdatePlanOrderRequest request) {
        return null;
    }

    @Override
    public ResponseObject<?> changePlanOrderStatus(String planOrderId) {
        return null;
    }

    @Override
    public ResponseObject<?> findPlanOrderById(String planOrderId) {
        return null;
    }
}
