package udpm.hn.metu.entity.base;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import udpm.hn.metu.infrastructure.listener.SubAuditEntityListener;

import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(SubAuditEntityListener.class)
public abstract class SubAuditEntity extends PrimaryEntity implements Serializable {

    @Column(name = "create_at")
    private Long createAt;

    @Column(name = "update_at")
    private Long updateAt;

    @Column(name = "deleted")
    private Boolean deleted;
}
