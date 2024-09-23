package udpm.hn.metu.entity.base;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import udpm.hn.metu.infrastructure.listener.AuditEntityListener;

import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditEntityListener.class)
public abstract class AuditEntity extends PrimaryEntity implements Serializable {

    @Column(name = "create_at")
    private Long createAt;

    @Column(name = "create_by")
    private String createBy;

    @Column(name = "update_at")
    private Long updateAt;

    @Column(name = "update_by")
    private String updateBy;

    @Column(name = "deleted")
    private Boolean deleted;

}
