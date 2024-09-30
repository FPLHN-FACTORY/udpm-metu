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
import org.hibernate.annotations.Nationalized;
import udpm.hn.metu.entity.base.AuditEntity;
import udpm.hn.metu.entity.base.PrimaryEntity;
import udpm.hn.metu.infrastructure.constant.EntityProperties;
import udpm.hn.metu.infrastructure.constant.enums.Status;

import java.io.Serializable;

@Entity
@Table(name = "channel")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tag extends AuditEntity implements Serializable {

    @ManyToOne
    @JoinColumn(name = "type_id")
    private Business business;

    @Column(name = "code", length = EntityProperties.LENGTH_CODE)
    private String code;

    @Column(name = "name", length = EntityProperties.LENGTH_NAME)
    @Nationalized
    private String name;

    @Column(name = "color", length = EntityProperties.LENGTH_CODE)
    @Nationalized
    private String color;

    @Column(name = "description", length = EntityProperties.LENGTH_DESCRIPTION)
    @Nationalized
    private String description;

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private Status status;

}
