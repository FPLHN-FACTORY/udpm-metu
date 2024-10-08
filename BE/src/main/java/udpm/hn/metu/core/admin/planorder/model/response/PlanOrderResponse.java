package udpm.hn.metu.core.admin.planorder.model.response;

import lombok.Getter;
import lombok.Setter;
import udpm.hn.metu.core.common.base.IsIdentify;
import udpm.hn.metu.entity.Business;
import udpm.hn.metu.entity.Plan;
import udpm.hn.metu.entity.PlanOrder;
import udpm.hn.metu.infrastructure.constant.enums.Status;

import java.util.List;

@Setter
@Getter
public class PlanOrderResponse {

    private String email;               // Email

    private Long createAt;              // Ngày tạo

    private Long expirationDate;               // Ngày hết hạn

    private Double price;               // Giá

    private Short quantity;           // Số lượng

    private Status status;             // Trạng thái

    private List<Business> businesses;

    private List<Plan> plans;

}
