package nl.broscience.Brochef.web.service.controller;

import nl.broscience.Brochef.web.service.dto.GoalDto;
import nl.broscience.Brochef.web.service.models.Customer;
import nl.broscience.Brochef.web.service.models.Goal;
import nl.broscience.Brochef.web.service.services.GoalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/goals")
public class GoalController {
private final GoalService service;

    public GoalController(GoalService service) {
        this.service = service;
    }


    @GetMapping("")
    public ResponseEntity<Iterable<GoalDto>> getAllGoals() {
        return ResponseEntity.ok(service.getAllGoals());
    }

    @GetMapping("{id}")
    public ResponseEntity<GoalDto> getGoal(@PathVariable Long id) {
        return ResponseEntity.ok(service.getGoalDtoById(id));
    }

    @PostMapping("")
    public ResponseEntity<String> createGoal(@RequestBody GoalDto goalDto) {
        Long savedGoal = service.createGoal(goalDto);

        URI uri = URI.create(
                ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("/goals/" + savedGoal).toUriString());
        return ResponseEntity.created(uri).body("Goal has been created");
    }

//
    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteGoal(@PathVariable Long id) {
        service.deleteGoal(id);
        return ResponseEntity.ok().body("Goal Deleted");
    }
    @PutMapping("{id}")
    public ResponseEntity<String> updateGoal(@PathVariable Long id, @RequestBody Goal newGoal) {
        service.updateGoal(id, newGoal);
        return ResponseEntity.ok().body("Goal Updated");
    }


}
