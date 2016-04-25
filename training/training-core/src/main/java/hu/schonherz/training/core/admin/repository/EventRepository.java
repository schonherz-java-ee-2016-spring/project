package hu.schonherz.training.core.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.schonherz.training.core.admin.entity.Event;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public interface EventRepository extends JpaRepository<Event, Long> {

	Event findEventByName(String name);

	Event findEventById(Long id);

	List<Event> findAllByType(String type);

	List<Event> findAllByPlace(String place);

}
