package udpm.hn.metu.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import udpm.hn.metu.entity.base.PrimaryEntity;
import udpm.hn.metu.infrastructure.constant.EntityProperties;
import udpm.hn.metu.infrastructure.constant.enums.ConversationType;
import udpm.hn.metu.infrastructure.constant.enums.Status;

import java.io.Serializable;

@Entity
@Table(name = "conversation")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Conversation extends PrimaryEntity implements Serializable {

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private Session session;

    @ManyToOne
    @JoinColumn(name = "current_agent")
    private User user;

    @Column(name = "code", length = EntityProperties.LENGTH_CODE)
    private String code;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private ConversationType type;

    @Column(name = "channel_internal", length = EntityProperties.LENGTH_NAME)
    private String channelInternal;

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private Status status;

    @Column(name = "create_at")
    private Long createAt;

}
