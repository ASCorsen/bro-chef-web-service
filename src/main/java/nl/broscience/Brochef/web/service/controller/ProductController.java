package nl.broscience.Brochef.web.service.controller;

import nl.broscience.Brochef.web.service.dto.ProductDto;
import nl.broscience.Brochef.web.service.models.Product;
import nl.broscience.Brochef.web.service.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService prodService;

    public ProductController(ProductService prodService) {
        this.prodService = prodService;
    }

    @PostMapping("{id}")
    public ResponseEntity<String> createProduct(@Valid @PathVariable Long id, @RequestBody ProductDto productDto, BindingResult br) {
        Long savedProduct = prodService.createProduct(productDto, id);
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
                            .path("/diets/" + savedProduct).toUriString());
            return ResponseEntity.created(uri).body("Product has been created");
        }
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
