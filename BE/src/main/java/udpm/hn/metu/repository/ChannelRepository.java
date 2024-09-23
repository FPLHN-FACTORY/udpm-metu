package udpm.hn.metu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import udpm.hn.metu.entity.Channel;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, String> {
}
