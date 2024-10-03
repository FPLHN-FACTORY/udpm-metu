package udpm.hn.metu.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;
import udpm.hn.metu.entity.base.SubAuditEntity;
import udpm.hn.metu.infrastructure.constant.EntityProperties;
import udpm.hn.metu.infrastructure.constant.enums.Role;
import udpm.hn.metu.infrastructure.constant.enums.Status;

import java.io.Serializable;

@Entity
@Table(name = "[User]")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User extends SubAuditEntity implements Serializable {

    @ManyToOne
    @JoinColumn(name = "business_id")
    private Business business;

    @Column(name = "email", length = EntityProperties.LENGTH_CONTENT, unique = true)
    private String email;

    @Column(name = "password", length = EntityProperties.LENGTH_PASSWORD)
    private String password;

    @Column(name = "password_secret", length = EntityProperties.LENGTH_PASSWORD_SECRET)
    private String passwordSecret;

    @Column(name = "role")
    @Enumerated(EnumType.ORDINAL)
    private Role role;

    @Column(name = "full_name", length = EntityProperties.LENGTH_NAME)
    @Nationalized
    private String fullName;

    @Column(name = "code", length = EntityProperties.LENGTH_CODE)
    @Nationalized
    private String code;

    @Column(name = "address", length = EntityProperties.LENGTH_CONTENT)
    @Nationalized
    private String address;

    @Column(name = "avatar_image", length = EntityProperties.LENGTH_IMAGE)
    private String avatarImage;

    @Column(name = "rating")
    private Short rating;

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private Status status;

    @Column(name = "is_online")
    private Boolean isOnline;

    @Column(name = "last_time_active")
    private Long lastTimeActive;

}
