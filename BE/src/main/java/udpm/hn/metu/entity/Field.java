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
import udpm.hn.metu.entity.base.SubAuditEntity;
import udpm.hn.metu.infrastructure.constant.EntityProperties;

import java.io.Serializable;

@Entity
@Table(name = "field")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Field extends SubAuditEntity implements Serializable {

    @ManyToOne
    @JoinColumn(name = "form_id")
    private ButtonForm buttonForm;

    @Column(name = "name", length = EntityProperties.LENGTH_NAME)
    private String name;

    @Column(name = "type", length = EntityProperties.LENGTH_CODE)
    private String type;

    @Column(name = "value")
    private String value;

    @Column(name = "require")
    private Boolean require;

}
