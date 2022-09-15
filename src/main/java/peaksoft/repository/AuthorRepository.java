package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.entity.Author;

public interface AuthorRepository extends JpaRepository<Author,Long> {
}
