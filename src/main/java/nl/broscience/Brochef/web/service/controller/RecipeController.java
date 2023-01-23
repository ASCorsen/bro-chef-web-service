package nl.broscience.Brochef.web.service.controller;


import nl.broscience.Brochef.web.service.models.Product;
import nl.broscience.Brochef.web.service.models.Recipe;
import nl.broscience.Brochef.web.service.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;


@RestController
@RequestMapping("/recipes")
public class RecipeController {
    @Autowired
    private RecipeRepository repos;

    @GetMapping("")
    public ResponseEntity<Iterable<Recipe>> getAllRecipes() { return ResponseEntity.ok(repos.findAll()); }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Recipe>> getRecipeById(@PathVariable Long id) {
        return ResponseEntity.ok(repos.findById(id)); }

    @PostMapping("")
    public ResponseEntity<String> createRecipe(@RequestBody Recipe recipe) {
        Recipe savedRecipe = repos.save(recipe);

        URI uri = URI.create(
                ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("/recipes/" + savedRecipe.getId()).toUriString());
        return ResponseEntity.created(uri).body("Recipe has been created!");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteRecipe(@PathVariable Long id) {
        repos.deleteById(id);
        return ResponseEntity.ok().body("Recipe has been Deleted");
    }

    @PutMapping("{id}")
    public ResponseEntity<String> deleteRecipe(@PathVariable Long id, @RequestBody Recipe newRecipe) {
        Recipe recipe = repos.findById(id).get();
        if (recipe != null){
            newRecipe.setId(id);
            recipe = newRecipe;
            repos.save(recipe);
            return ResponseEntity.ok().body("Recipe has been Updated");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
