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
@Table(name = "button")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Button extends SubAuditEntity implements Serializable {

    @ManyToOne
    @JoinColumn(name = "widget_id")
    private Widget widget;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @Column(name = "title", length = EntityProperties.LENGTH_NAME)
    private String title;

    @Column(name = "tooltip", length = EntityProperties.LENGTH_CONTENT)
    private String tooltip;

    @Column(name = "icon_url", length = EntityProperties.LENGTH_URL)
    private String iconUrl;

    @Column(name = "high_light")
    private Boolean highLight;

    @Column(name = "description", length = EntityProperties.LENGTH_DESCRIPTION)
    private String description;


}
