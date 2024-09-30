package udpm.hn.metu.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import udpm.hn.metu.entity.base.SubAuditEntity;

import java.io.Serializable;

@Entity
@Table(name = "session")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Session extends SubAuditEntity implements Serializable {


    @Column(name = "user_agent", length = 255)
    private String userAgent;

    @Column(name = "ip", length = 50)
    private String ip;

    @Column(name = "browser_name", length = 50)
    private String browserName;

    @Column(name = "browser_version", length = 30)
    private String browserVersion;

}
