package nl.broscience.Brochef.web.service.services;

import nl.broscience.Brochef.web.service.dto.DietDto;
import nl.broscience.Brochef.web.service.dto.ProductDto;
import nl.broscience.Brochef.web.service.dto.RecipeDto;
import nl.broscience.Brochef.web.service.exceptions.RecordNotFoundException;
import nl.broscience.Brochef.web.service.models.Diet;
import nl.broscience.Brochef.web.service.models.Goal;
import nl.broscience.Brochef.web.service.models.Product;
import nl.broscience.Brochef.web.service.models.Recipe;
import nl.broscience.Brochef.web.service.repositories.ProductRepository;
import nl.broscience.Brochef.web.service.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductService {
    private final ProductRepository prodRepo;
    private final RecipeRepository recRepo;

    public ProductService(ProductRepository prodRepo, RecipeRepository recRepo) {
        this.prodRepo = prodRepo;
        this.recRepo = recRepo;
    }

    public Long createProduct(ProductDto productDto, Long recipeId) {
        if (recRepo.findById(recipeId).isPresent()) {
            Product newProduct = new Product();

            newProduct.setName(productDto.name);
            newProduct.setDescription(productDto.description);

            Recipe recipeObject = recRepo.findById(recipeId).get();
            newProduct.setRecipe(recipeObject);

            Product savedProduct = prodRepo.save(newProduct);
            return savedProduct.getId();

        } else {
            throw new RecordNotFoundException("No Product has been found with this ID");
        }
    }
    public Iterable<ProductDto> getAllProducts() {
        Iterable<Product> productList = prodRepo.findAll();
        ArrayList<ProductDto> productDtoList = new ArrayList<>();

        for (Product product : productList) {
            ProductDto newProductDto = new ProductDto(product);
            productDtoList.add(newProductDto);
        }
        return productDtoList;
    }

    public ProductDto getProductById(Long id) {
        if (prodRepo.findById(id).isPresent()) {
            Product product = prodRepo.findById(id).get();
            ProductDto newProductDto = new ProductDto(product);
            return newProductDto;
        } else {
            throw new RecordNotFoundException("No Goal found with this ID");
        }
    }
    public void deletePRoduct(Long id) {
        if (prodRepo.findById(id).isPresent()) {
            prodRepo.deleteById(id);
        } else {
            throw new RecordNotFoundException("No Diet found with this ID");
        }
    }
    public ProductDto updateProduct(Long id, Product newProduct) {

        if(prodRepo.findById(id).isPresent()) {
            newProduct.setId(id);
            prodRepo.save(newProduct);
            return new ProductDto(newProduct);
        }
        else {
            throw new RecordNotFoundException("No Diet found with this ID");
        }
    }
        public void deleteProduct(Long id) {
        if (prodRepo.findById(id).isPresent()) {
            prodRepo.deleteById(id);
        } else {
            throw new RecordNotFoundException("No Product found with this ID");
        }
    }

}
