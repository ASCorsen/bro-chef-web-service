package nl.broscience.Brochef.web.service.controller;

import nl.broscience.Brochef.web.service.dto.DietDto;
import nl.broscience.Brochef.web.service.models.Diet;
import nl.broscience.Brochef.web.service.services.DietService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/diets")
public class DietController {

private final DietService dietservice;

    public DietController(DietService dietservice) {
        this.dietservice = dietservice;
    }

    @GetMapping("")
    public ResponseEntity<Iterable<DietDto>> getAllGoals() {
        return ResponseEntity.ok(dietservice.getAllDiet());
    }

    @GetMapping("{id}")
    public ResponseEntity<DietDto> getDiet(@PathVariable Long id) {
        return ResponseEntity.ok(dietservice.getDietById(id));
    }

    @PostMapping("{id}")
    public ResponseEntity<String> createDiet(@Valid @PathVariable Long id, @RequestBody DietDto dietDto, BindingResult br) {

        Long savedDiet = dietservice.createDiet(dietDto, id);
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
                            .path("/diets/" + savedDiet).toUriString());
            return ResponseEntity.created(uri).body("Diet has been created");
        }

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteDiet(@PathVariable Long id) {
        dietservice.deleteDiet(id);
        return ResponseEntity.ok().body("Diet has been Deleted");
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateDiet(@PathVariable Long id, @RequestBody Diet newDiet) {
        dietservice.updateDiet(id, newDiet);
        return ResponseEntity.ok().body("Diet Updated");
    }

}
