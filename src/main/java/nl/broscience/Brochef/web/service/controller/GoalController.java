package nl.broscience.Brochef.web.service.controller;

import nl.broscience.Brochef.web.service.dto.GoalDto;
import nl.broscience.Brochef.web.service.models.Customer;
import nl.broscience.Brochef.web.service.models.Goal;
import nl.broscience.Brochef.web.service.services.GoalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
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
    public ResponseEntity<String> createGoal(@Valid @RequestBody GoalDto goalDto, BindingResult br) {
        Long savedGoal = service.createGoal(goalDto);
        if (br.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            for (FieldError fe : br.getFieldErrors()) {
                sb.append(fe.getField() + ": ");
                sb.append(fe.getDefaultMessage());
                sb.append("\n");
            }
            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
        } else {

            URI uri = URI.create(
                    ServletUriComponentsBuilder
                            .fromCurrentContextPath()
                            .path("/goals/" + savedGoal).toUriString());
            return ResponseEntity.created(uri).body("Goal has been created");
        }
    }

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
