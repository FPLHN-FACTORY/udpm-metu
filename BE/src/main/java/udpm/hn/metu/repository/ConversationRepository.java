package udpm.hn.metu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import udpm.hn.metu.entity.Conversation;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, String> {
}
