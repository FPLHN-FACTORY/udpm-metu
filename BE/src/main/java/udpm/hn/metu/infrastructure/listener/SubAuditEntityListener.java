package udpm.hn.metu.infrastructure.listener;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import udpm.hn.metu.entity.base.AuditEntity;
import udpm.hn.metu.entity.base.SubAuditEntity;

import java.util.Calendar;

public class SubAuditEntityListener {

    @PrePersist
    private void onCreate(SubAuditEntity entity) {
        entity.setCreateAt(getCurrentTime());
        entity.setUpdateAt(getCurrentTime());
        entity.setDeleted(false);
    }

    @PreUpdate
    private void onUpdate(SubAuditEntity entity) {
        entity.setUpdateAt(getCurrentTime());
    }

    private long getCurrentTime() {
        return Calendar.getInstance().getTimeInMillis();
    }
}
