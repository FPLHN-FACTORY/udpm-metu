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
import udpm.hn.metu.entity.base.PrimaryEntity;
import udpm.hn.metu.entity.base.SubAuditEntity;
import udpm.hn.metu.infrastructure.constant.EntityProperties;

import java.io.Serializable;

@Entity
@Table(name = "domain")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Domain extends SubAuditEntity implements Serializable {

    @ManyToOne
    @JoinColumn(name = "business_id")
    private Business business;

    @Column(name = "code", length = EntityProperties.LENGTH_CODE)
    private String code;

    @Column(name = "name", length = EntityProperties.LENGTH_NAME)
    private String name;

    @Column(name = "status")
    private Short status;


}
