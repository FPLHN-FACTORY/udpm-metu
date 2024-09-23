package udpm.hn.metu.entity.base;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import udpm.hn.metu.infrastructure.constant.EntityProperties;
import udpm.hn.metu.infrastructure.listener.EntityListener;

import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(EntityListener.class)
public abstract class PrimaryEntity implements Serializable {

    @Id
    @Column(length = EntityProperties.LENGTH_ID, updatable = false)
    private String id;

}
