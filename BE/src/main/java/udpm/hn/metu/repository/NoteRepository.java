package udpm.hn.metu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import udpm.hn.metu.entity.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, String> {
}
