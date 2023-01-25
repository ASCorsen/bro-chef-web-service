package nl.broscience.Brochef.web.service.controller;


import nl.broscience.Brochef.web.service.dto.DietDto;
import nl.broscience.Brochef.web.service.dto.ProductDto;
import nl.broscience.Brochef.web.service.models.Diet;
import nl.broscience.Brochef.web.service.models.Product;
import nl.broscience.Brochef.web.service.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/products")
public class ProductController {
private final ProductService prodService;

    public ProductController(ProductService prodService) {
        this.prodService = prodService;
    }
    @PostMapping("{id}")
    public ResponseEntity<String> createProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        Long savedProduct = prodService.createProduct(productDto, id);



        URI uri = URI.create(
                ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("/diets/" + savedProduct).toUriString());
        return ResponseEntity.created(uri).body("Product has been created");
    }

    @GetMapping("")
    public ResponseEntity<Iterable<ProductDto>> getAllProducts() {
        return ResponseEntity.ok(prodService.getAllProducts());
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(prodService.getProductById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updatePRoduct(@PathVariable Long id, @RequestBody Product newProduct) {
        prodService.updateProduct(id, newProduct);
        return ResponseEntity.ok().body("Product has been Updated");
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable Long id) {
        prodService.deleteProduct(id);
        return ResponseEntity.ok().body("Product has been Deleted");
    }
}
