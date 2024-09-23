package udpm.hn.metu.infrastructure.listener;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import udpm.hn.metu.entity.base.AuditEntity;

import java.util.Calendar;
public class AuditEntityListener {

    @PrePersist
    private void onCreate(AuditEntity entity) {
        entity.setCreateAt(getCurrentTime());
        entity.setUpdateAt(getCurrentTime());
        entity.setDeleted(false);
    }

    @PreUpdate
    private void onUpdate(AuditEntity entity) {
        entity.setUpdateAt(getCurrentTime());
    }

    private long getCurrentTime() {
        return Calendar.getInstance().getTimeInMillis();
    }

}
