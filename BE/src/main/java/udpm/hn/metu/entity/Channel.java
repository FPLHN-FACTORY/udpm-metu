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
import udpm.hn.metu.infrastructure.constant.EntityProperties;

import java.io.Serializable;

@Entity
@Table(name = "channel")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Channel extends AuditEntity implements Serializable {

    @ManyToOne
    @JoinColumn(name = "business_id")
    private Business business;

    @Column(name = "code", length = EntityProperties.LENGTH_CODE)
    private String code;

    @Column(name = "name", length = EntityProperties.LENGTH_NAME)
    private String name;

    @Column(name = "description", length = EntityProperties.LENGTH_DESCRIPTION)
    private String description;

    @Column(name = "channelization_type")
    private Short channelizationType;

    @Column(name = "channelization_value", length = EntityProperties.LENGTH_CONTENT)
    private String channelizationValue;

    @Column(name = "offline_redirecter", length = EntityProperties.LENGTH_CONTENT)
    private String offlineRedirecter;

}
