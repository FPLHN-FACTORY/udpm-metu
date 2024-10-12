package udpm.hn.metu.core.admin.planorder.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import udpm.hn.metu.core.common.base.IsIdentify;
import udpm.hn.metu.entity.Business;
import udpm.hn.metu.entity.Plan;
import udpm.hn.metu.entity.PlanOrder;
import udpm.hn.metu.infrastructure.constant.enums.Status;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PlanOrderResponse {

    private String email;               // Email

    private Long createAt;              // Ngày tạo

    private Long expirationDate;               // Ngày hết hạn

    private Double price;               // Giá

    private Short quantity;           // Số lượng

    private Status status;             // Trạng thái

    private String businessName;

    private String planName;

}
