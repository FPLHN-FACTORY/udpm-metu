package udpm.hn.metu.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;
import udpm.hn.metu.entity.base.AuditEntity;
import udpm.hn.metu.infrastructure.constant.EntityProperties;

import java.io.Serializable;

@Entity
@Table(name = "business_type")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BusinessType extends AuditEntity implements Serializable {

    @Column(name = "code", length = EntityProperties.LENGTH_CODE)
    private String code;

    @Column(name = "name", length = EntityProperties.LENGTH_NAME)
    @Nationalized
    private String name;

    @Column(name = "description", length = EntityProperties.LENGTH_DESCRIPTION)
    @Nationalized
    private String description;


}
