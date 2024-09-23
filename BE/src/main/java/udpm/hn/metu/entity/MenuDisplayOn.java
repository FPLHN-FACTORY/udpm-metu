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
import udpm.hn.metu.entity.base.PrimaryEntity;
import udpm.hn.metu.entity.base.SubAuditEntity;
import udpm.hn.metu.infrastructure.constant.EntityProperties;

import java.io.Serializable;

@Getter
@Setter
@Table(name = "menu_display_on")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class MenuDisplayOn extends SubAuditEntity implements Serializable {

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @Column(name = "value", length = EntityProperties.LENGTH_CONTENT)
    private String value;

}
