package nl.broscience.Brochef.web.service.controller;

import nl.broscience.Brochef.web.service.models.Customer;
import nl.broscience.Brochef.web.service.models.Goal;
import nl.broscience.Brochef.web.service.models.Product;
import nl.broscience.Brochef.web.service.models.Recipe;
import nl.broscience.Brochef.web.service.repositories.CustomerRepository;
import nl.broscience.Brochef.web.service.repositories.GoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerRepository repos;

    @Autowired
    private GoalRepository goalRepo;

    @GetMapping("")
    public ResponseEntity<Iterable<Customer>> getAllCustomer() { return ResponseEntity.ok(repos.findAll()); }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Customer>> getCustomer(@PathVariable Long id) {
        return ResponseEntity.ok(repos.findById(id)); }

    @PostMapping("")
    public ResponseEntity<String> createCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = repos.save(customer);

        URI uri = URI.create(
                ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("/customers/" + savedCustomer.getId()).toUriString());
        return ResponseEntity.created(uri).body("Customer created!");
    }

//    @PostMapping("{id}")
//    public ResponseEntity<String> createCustomer(@PathVariable Long id, @RequestBody Customer customer) {
//        Goal goal = goalRepo.findById(id).get();
//
//        customer.setGoal(goal);
//
//        Customer savedCustomer = repos.save(customer);
//
//        URI uri = URI.create(
//                ServletUriComponentsBuilder
//                        .fromCurrentContextPath()
//                        .path("/customers/" + savedCustomer.getId()).toUriString());
//        return ResponseEntity.created(uri).body("Customer has been saved");
//    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable Long id) {
        repos.deleteById(id);
        return ResponseEntity.ok().body("Customer Deleted");
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateCustomer(@PathVariable Long id, @RequestBody Customer newCustomer) {
        Customer customer = repos.findById(id).get();
        if (customer != null){
            newCustomer.setId(id);
            customer = newCustomer;
            repos.save(customer);
            return ResponseEntity.ok().body("Customer Updated");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
