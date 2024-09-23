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
import udpm.hn.metu.entity.base.PrimaryEntity;
import udpm.hn.metu.infrastructure.constant.EntityProperties;
import udpm.hn.metu.infrastructure.constant.enums.MessageContentType;

import java.io.Serializable;

@Entity
@Table(name = "message")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Message extends PrimaryEntity implements Serializable {

    @ManyToOne
    @JoinColumn(name = "conversation_id")
    private Conversation conversation;

    @Column(name = "sender", length = EntityProperties.LENGTH_NAME)
    private String sender;

    @Column(name = "sender_type")
    private Boolean senderType;

    @Column(name = "content", length = EntityProperties.LENGTH_CONTENT)
    private String content;

    @Column(name = "content_type")
    @Enumerated(EnumType.STRING)
    private MessageContentType contentType;

    @Column(name = "create_at")
    private Long createAt;
}
