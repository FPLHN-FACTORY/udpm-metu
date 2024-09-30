package udpm.hn.metu.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import udpm.hn.metu.entity.base.PrimaryEntity;
import udpm.hn.metu.infrastructure.constant.EntityProperties;
import udpm.hn.metu.infrastructure.constant.enums.Status;

import java.io.Serializable;


@Entity
@Table(name = "plan_order")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlanOrder extends PrimaryEntity implements Serializable {

    @ManyToOne
    @JoinColumn(name = "business_id")
    private Business business;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    private Plan plan;

    @Column(name = "email", length = EntityProperties.LENGTH_CONTENT)
    private String email;

    @Column(name = "quantity")
    private Short quantity;

    @Column(name = "price")
    private Double price;

    @Column(name = "number_domain")
    private Short numberDomain;

    @Column(name = "number_menu")
    private Short numberMenu;

    @Column(name = "number_staff")
    private Short numberStaff;

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private Status status;

    @Column(name = "exp_date")
    private Long expirationDate;

    @Column(name = "create_at")
    private Long createAt;
}
