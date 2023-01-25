package nl.broscience.Brochef.web.service.controller;


import nl.broscience.Brochef.web.service.dto.RecipeDto;
import nl.broscience.Brochef.web.service.models.Goal;
import nl.broscience.Brochef.web.service.models.Recipe;
import nl.broscience.Brochef.web.service.services.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/recipes")
public class RecipeController {
private final RecipeService service;

    public RecipeController(RecipeService service) {
        this.service = service;
    }



    @GetMapping("")
    public ResponseEntity<Iterable<RecipeDto>> getAllRecipes() {
        return ResponseEntity.ok(service.getAllRecipes());
    }

    @GetMapping("{id}")
    public ResponseEntity<RecipeDto> getRecipeById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getRecipeById(id));
    }

    @PostMapping("")
    public ResponseEntity<String> createRecipe(@RequestBody RecipeDto recipeDto) {
       Long  savedRecipe = service.createRecipe(recipeDto);

        URI uri = URI.create(
                ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("/recipes/" + savedRecipe).toUriString());
        return ResponseEntity.created(uri).body("Recipe has been created!");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteRecipe(@PathVariable Long id) {
        service.deleteRecipe(id);
        return ResponseEntity.ok().body("Recipe has been Deleted");
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateRecipe(@PathVariable Long id, @RequestBody Recipe newRecipe) {
        service.updateRecipe(id, newRecipe);
        return ResponseEntity.ok().body("Recipe Updated");
    }

}
