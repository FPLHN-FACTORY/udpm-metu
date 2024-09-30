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
@Table(name = "greeting")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Greeting extends AuditEntity implements Serializable {

    @ManyToOne
    @JoinColumn(name = "type_id")
    private Business business;

    @Column(name = "code", length = EntityProperties.LENGTH_CODE)
    private String code;

    @Column(name = "title", length = EntityProperties.LENGTH_NAME)
    private String title;

    @Column(name = "display_type")
    private Boolean displayType;

    @Column(name = "duration")
    private Float duration;

    @Column(name = "display_url_type")
    private Boolean displayUrlType;

    @Column(name = "display_url_values", length = EntityProperties.LENGTH_DESCRIPTION)
    private String displayUrlValues;

    @Column(name = "description", length = EntityProperties.LENGTH_DESCRIPTION)
    private String description;


}
