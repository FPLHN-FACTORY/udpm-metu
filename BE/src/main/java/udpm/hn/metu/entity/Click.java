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

import java.io.Serializable;

@Entity
@Table(name = "click")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Click extends PrimaryEntity implements Serializable {

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Button button;

    @ManyToOne
    @JoinColumn(name = "url_id")
    private Url url;

    @Column(name = "create_at")
    private Long createAt;

}
