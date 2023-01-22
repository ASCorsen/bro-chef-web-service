package nl.broscience.Brochef.web.service.controller;

import nl.broscience.Brochef.web.service.models.Customer;
import nl.broscience.Brochef.web.service.models.Goal;
import nl.broscience.Brochef.web.service.repositories.GoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/goals")
public class GoalController {
    @Autowired
    private GoalRepository repos;

    @GetMapping("")
    public ResponseEntity<Iterable<Goal>> getAllCustomer() { return ResponseEntity.ok(repos.findAll()); }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Goal>> getCustomer(@PathVariable Long id) {
        return ResponseEntity.ok(repos.findById(id)); }

    @PostMapping("")
    public ResponseEntity<String> createGoal(@RequestBody Goal goal) {
        Goal savedGoal = repos.save(goal);

        URI uri = URI.create(
                ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("/goals/" + savedGoal.getId()).toUriString());
        return ResponseEntity.created(uri).body("Goal has been created");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteGoal(@PathVariable Long id) {
        repos.deleteById(id);
        return ResponseEntity.ok().body("Goal Deleted");
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateGoal(@PathVariable Long id, @RequestBody Goal newGoal) {
        Goal goal = repos.findById(id).get();
        if (goal != null){
            newGoal.setId(id);
            goal = newGoal;
            repos.save(goal);
            return ResponseEntity.ok().body("Goal Updated");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
