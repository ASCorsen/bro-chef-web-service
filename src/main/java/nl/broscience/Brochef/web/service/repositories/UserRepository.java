package nl.broscience.Brochef.web.service.repositories;

import nl.broscience.Brochef.web.service.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
}
