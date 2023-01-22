package nl.broscience.Brochef.web.service.repositories;

import nl.broscience.Brochef.web.service.models.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
