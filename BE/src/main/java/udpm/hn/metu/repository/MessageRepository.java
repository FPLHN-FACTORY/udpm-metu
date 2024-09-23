package udpm.hn.metu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import udpm.hn.metu.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, String> {
}
