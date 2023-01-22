package nl.broscience.Brochef.web.service.repositories;

import nl.broscience.Brochef.web.service.models.Goal;
import org.springframework.data.repository.CrudRepository;

public interface GoalRepository extends CrudRepository<Goal, Long> {
}
