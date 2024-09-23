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
import udpm.hn.metu.infrastructure.constant.enums.ConversationFormType;
import udpm.hn.metu.infrastructure.constant.EntityProperties;

import java.io.Serializable;

@Entity
@Table(name = "conversation_form")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConversationForm extends PrimaryEntity implements Serializable {

    @ManyToOne
    @JoinColumn(name = "conversation_id")
    private Conversation conversation;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private ConversationFormType type;

    @Column(name = "title", length = EntityProperties.LENGTH_CONTENT)
    private String title;

    @Column(name = "value", length = EntityProperties.LENGTH_CONTENT)
    private String value;

}
