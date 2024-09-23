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
import udpm.hn.metu.entity.base.PrimaryEntity;
import udpm.hn.metu.infrastructure.constant.EntityProperties;

import java.io.Serializable;

@Entity
@Table(name = "shape")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Shape  extends AuditEntity implements Serializable {

    @Column(name = "code", length = EntityProperties.LENGTH_CODE)
    private String code;

    @Column(name = "name", length = EntityProperties.LENGTH_NAME)
    private String name;

    @Column(name = "class_name", length = EntityProperties.LENGTH_NAME)
    private String className;

    @Column(name = "icon_url", length = EntityProperties.LENGTH_URL)
    private String iconUrl;

    @Column(name = "description", length = EntityProperties.LENGTH_DESCRIPTION)
    private String description;

    @Column(name = "status")
    private Short status;

}
