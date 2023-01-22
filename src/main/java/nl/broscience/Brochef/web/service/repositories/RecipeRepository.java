package nl.broscience.Brochef.web.service.repositories;

import nl.broscience.Brochef.web.service.models.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
