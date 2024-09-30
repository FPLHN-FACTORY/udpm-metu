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
@Table(name = "button_form")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ButtonForm extends SubAuditEntity implements Serializable {

    @ManyToOne
    @JoinColumn(name = "button_id")
    private Button button;

    @Column(name = "name", length = EntityProperties.LENGTH_NAME)
    private String name;

    @Column(name = "type", length = EntityProperties.LENGTH_CODE)
    private String type;

    @Column(name = "value", length = EntityProperties.LENGTH_CONTENT)
    private String value;

    @Column(name = "target")
    private Boolean target;

    @Column(name = "require")
    private Boolean require;

}
