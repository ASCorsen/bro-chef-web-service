package nl.broscience.Brochef.web.service.controller;


import nl.broscience.Brochef.web.service.models.Product;
import nl.broscience.Brochef.web.service.models.Recipe;
import nl.broscience.Brochef.web.service.repositories.ProductRepository;
import nl.broscience.Brochef.web.service.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductRepository repos;
    @Autowired
    private RecipeRepository recipeRepo;

    @GetMapping("")
    public ResponseEntity<Iterable<Product>> getAllProducts() {
        return ResponseEntity.ok(repos.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Product>> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(repos.findById(id));
    }

    @PostMapping("{id}")
    public ResponseEntity<String> createProduct(@PathVariable Long id, @RequestBody Product product) {
        Recipe recipe = recipeRepo.findById(id).get();
        product.setRecipe(recipe);

        Product savedProduct = repos.save(product);

        URI uri = URI.create(
                ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("/products/" + savedProduct.getId()).toUriString());
        return ResponseEntity.created(uri).body("Product has been created!");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable Long id) {
        repos.deleteById(id);
        return ResponseEntity.ok().body("Product has been Deleted");
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody Product newProduct) {
        Product product = repos.findById(id).get();
        if (product != null) {
            newProduct.setId(product.getId());
            product = newProduct;
            repos.save(product);
            return ResponseEntity.ok().body("Product has been Updated");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
