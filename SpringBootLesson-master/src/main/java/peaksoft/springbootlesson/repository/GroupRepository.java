package peaksoft.springbootlesson.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import peaksoft.springbootlesson.entity.Group;
@Repository
public interface GroupRepository extends JpaRepository<Group,Long> {
}
