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
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Nationalized;
import udpm.hn.metu.entity.base.AuditEntity;
import udpm.hn.metu.entity.base.PrimaryEntity;
import udpm.hn.metu.infrastructure.constant.EntityProperties;

import java.io.Serializable;

@Entity
@Table(name = "menu")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class Menu extends AuditEntity implements Serializable {

    @ManyToOne
    @JoinColumn(name = "shape_id")
    private Shape shape;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;

    @Column(name = "code", length = EntityProperties.LENGTH_CODE)
    private String code;

    @Column(name = "name", length = EntityProperties.LENGTH_NAME)
    private String name;

    @Column(name = "status")
    private Short status;

    @Column(name = "description", length = EntityProperties.LENGTH_DESCRIPTION)
    private String description;


    @Column(name = "device_type")
    private Short deviceType;

    @Column(name = "display_option")
    private Short displayOption;

    @Column(name = "icon_color", length = EntityProperties.LENGTH_CODE)
    private String  iconColor;

    @Column(name = "text_color", length = EntityProperties.LENGTH_CODE)
    private String  textColor;

    @Column(name = "background_color", length = EntityProperties.LENGTH_CODE)
    private String  backgroundColor;

    @Column(name = "opacity", length = EntityProperties.LENGTH_CODE)
    private Float  opacity;

    @Column(name = "appear_from")
    private Short appearFrom;

    @Column(name = "appear_to")
    private Short appearTo;

    @Column(name = "scroll_type")
    private Boolean scrollType;

    @Column(name = "scroll_value")
    private Float scrollValue;

    @Column(name = "time_show")
    private Float timeShow;

    @Column(name = "time_hidden")
    private Float timeHidden;

}
