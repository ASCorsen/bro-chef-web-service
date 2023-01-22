package nl.broscience.Brochef.web.service.controller;

import nl.broscience.Brochef.web.service.models.Customer;
import nl.broscience.Brochef.web.service.models.Diet;
import nl.broscience.Brochef.web.service.repositories.DietRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/diets")
public class DietController {

    @Autowired
    private DietRepository repos;

    @GetMapping("")
    public ResponseEntity<Iterable<Diet>> getAllGoals() { return ResponseEntity.ok(repos.findAll()); }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Diet>> getDiet(@PathVariable Long id) {
        return ResponseEntity.ok(repos.findById(id)); }

    @PostMapping("")
    public ResponseEntity<String> createDiet(@RequestBody Diet diet) {
        Diet savedDiet = repos.save(diet);

        URI uri = URI.create(
                ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("/diets/" + savedDiet.getId()).toUriString());
        return ResponseEntity.created(uri).body("Diet has been created");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteDiet(@PathVariable Long id) {
        repos.deleteById(id);
        return ResponseEntity.ok().body("Diet has been Deleted");
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateDiet(@PathVariable Long id, @RequestBody Diet newDiet) {
        Diet diet = repos.findById(id).get();
        if (diet != null){
            newDiet.setId(id);
            diet = newDiet;
            repos.save(diet);
            return ResponseEntity.ok().body("Diet has been Updated");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
