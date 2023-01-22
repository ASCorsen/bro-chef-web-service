package nl.broscience.Brochef.web.service.repositories;

import nl.broscience.Brochef.web.service.models.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
