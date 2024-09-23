package udpm.hn.metu.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import udpm.hn.metu.entity.base.AuditEntity;
import udpm.hn.metu.entity.base.PrimaryEntity;
import udpm.hn.metu.infrastructure.constant.EntityProperties;

import java.io.Serializable;

@Entity
@Table(name = "customer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends AuditEntity implements Serializable {

    @ManyToOne
    @JoinColumn(name = "business_id")
    private Business business;

    @Column(name = "code", length = EntityProperties.LENGTH_CODE)
    private String code;

    @Column(name = "name", length = EntityProperties.LENGTH_NAME)
    private String name;

    @Column(name = "email", length = EntityProperties.LENGTH_CONTENT)
    private String email;

    @Column(name = "phone_number", length = EntityProperties.LENGTH_PHONE_NUMBER)
    private String phoneNumber;

    @Column(name = "address", length = EntityProperties.LENGTH_CONTENT)
    private String address;

}
