package udpm.hn.metu.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;
import udpm.hn.metu.entity.base.AuditEntity;
import udpm.hn.metu.infrastructure.constant.EntityProperties;
import udpm.hn.metu.infrastructure.constant.enums.WidgetType;

import java.io.Serializable;

@Entity
@Table(name = "widget")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Widget extends AuditEntity implements Serializable {

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "code", length = EntityProperties.LENGTH_CODE)
    private String code;

    @Column(name = "name", length = EntityProperties.LENGTH_NAME)
    private String name;

    @Lob
    @Column(name = "icon_url", length = EntityProperties.LENGTH_URL)
    private String iconUrl;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private WidgetType type;

    @Column(name = "description", length = EntityProperties.LENGTH_DESCRIPTION)
    @Nationalized
    private String description;

    @Column(name = "top")
    private Boolean top;


}
