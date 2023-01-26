package nl.broscience.Brochef.web.service.services;

import nl.broscience.Brochef.web.service.dto.RecipeDto;
import nl.broscience.Brochef.web.service.models.Product;
import nl.broscience.Brochef.web.service.models.Recipe;
import nl.broscience.Brochef.web.service.repositories.ProductRepository;
import nl.broscience.Brochef.web.service.repositories.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    ProductRepository productRepository;

    @Mock
    RecipeRepository recipeRepository;

    @InjectMocks
    RecipeService service;

    @Test
    void shouldReturnProduct() {
        Recipe r = new Recipe();
        r.setVegetarian(false);
        r.setVegan(false);
        r.setName("Wurst Mit Saurkraut");
        r.setId(1L);


// org.mockito.exceptions.misusing.UnnecessaryStubbingException:
//Unnecessary stubbings detected

//        Product p = new Product();
//        p.setDescription("Ist sehr saur jah");
//        p.setName("Saurkraut");
//        p.setId(2L);
//        p.setRecipe(r);
//
//        List<Product> ps = new ArrayList();
//        ps.add(p);
//        r.setProducts(ps);
        Mockito.when(recipeRepository.findById(1L)).thenReturn(Optional.of(r));

//Mockito.when(productRepository.findById(2L)).thenReturn(Optional.of(p));

        RecipeDto dto = service.getRecipeById(1L);

        assertEquals("Wurst Mit Saurkraut", dto.name);

    }

}