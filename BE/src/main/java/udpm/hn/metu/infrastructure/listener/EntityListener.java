package udpm.hn.metu.infrastructure.listener;

import jakarta.persistence.PrePersist;
import udpm.hn.metu.entity.base.PrimaryEntity;

import java.util.UUID;

public class EntityListener {

    @PrePersist
    private void onCreate(PrimaryEntity entity) {
        entity.setId(UUID.randomUUID().toString());
    }

}
