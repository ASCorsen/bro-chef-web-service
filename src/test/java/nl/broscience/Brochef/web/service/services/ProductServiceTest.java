package nl.broscience.Brochef.web.service.services;

import nl.broscience.Brochef.web.service.dto.ProductDto;
import nl.broscience.Brochef.web.service.exceptions.DeleteRecordException;
import nl.broscience.Brochef.web.service.exceptions.NoRelatedObjectFoundException;
import nl.broscience.Brochef.web.service.exceptions.RecordNotFoundException;
import nl.broscience.Brochef.web.service.exceptions.UpdateRecordException;
import nl.broscience.Brochef.web.service.models.Product;
import nl.broscience.Brochef.web.service.models.Recipe;
import nl.broscience.Brochef.web.service.repositories.ProductRepository;
import nl.broscience.Brochef.web.service.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class ProductServiceTest {

    @Mock
    private ProductRepository mockProdRepo;
    @Mock
    private RecipeRepository mockRecRepo;

    private ProductService productServiceUnderTest;

    @BeforeEach
    void setUp() {
        productServiceUnderTest = new ProductService(mockProdRepo, mockRecRepo);
    }

    @Test
    void ShouldCreateProduct() {
        final Recipe recipe = new Recipe();
        final Product product = new Product();
        product.setId(0L);
        product.setName("name");
        product.setDescription("description");
        recipe.setProducts(List.of(product));
        recipe.setId(0L);
        recipe.setName("name");
        recipe.setVegan(false);
        recipe.setVegetarian(false);
        final ProductDto productDto = new ProductDto("name", "description", recipe);

        final Recipe recipe2 = new Recipe();
        final Product product1 = new Product();
        product1.setId(0L);
        product1.setName("name");
        product1.setDescription("description");
        recipe2.setProducts(List.of(product1));
        recipe2.setId(0L);
        recipe2.setName("name");
        recipe2.setVegan(false);
        recipe2.setVegetarian(false);
        final Optional<Recipe> recipe1 = Optional.of(recipe2);
        when(mockRecRepo.findById(0L)).thenReturn(recipe1);

        final Product product2 = new Product();
        final Recipe recipe3 = new Recipe();
        recipe3.setProducts(List.of(new Product()));
        recipe3.setId(0L);
        recipe3.setName("name");
        recipe3.setVegan(false);
        recipe3.setVegetarian(false);
        product2.setRecipe(recipe3);
        product2.setId(0L);
        product2.setName("name");
        product2.setDescription("description");
        when(mockProdRepo.save(any(Product.class))).thenReturn(product2);

        final Long result = productServiceUnderTest.createProduct(productDto, 0L);

        assertThat(result).isEqualTo(0L);
    }

    @Test
    void CheckObjectNotFoundException() {
        final Recipe recipe = new Recipe();
        final Product product = new Product();
        product.setId(0L);
        product.setName("name");
        product.setDescription("description");
        recipe.setProducts(List.of(product));
        recipe.setId(0L);
        recipe.setName("name");
        recipe.setVegan(false);
        recipe.setVegetarian(false);
        final ProductDto productDto = new ProductDto("name", "description", recipe);
        when(mockRecRepo.findById(0L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> productServiceUnderTest.createProduct(productDto, 0L))
                .isInstanceOf(NoRelatedObjectFoundException.class);
    }

    @Test
    void ShouldGetAllProducts() {

        final Product product = new Product();
        final Recipe recipe = new Recipe();
        recipe.setProducts(List.of(new Product()));
        recipe.setId(0L);
        recipe.setName("name");
        recipe.setVegan(false);
        recipe.setVegetarian(false);
        product.setRecipe(recipe);
        product.setId(0L);
        product.setName("name");
        product.setDescription("description");
        final Iterable<Product> products = List.of(product);
        when(mockProdRepo.findAll()).thenReturn(products);

        final Iterable<ProductDto> result = productServiceUnderTest.getAllProducts();

    }

    @Test
    void CheckProductRepo() {
        when(mockProdRepo.findAll()).thenReturn(Collections.emptyList());

        final Iterable<ProductDto> result = productServiceUnderTest.getAllProducts();

    }

    @Test
    void ShouldGetProductById() {
        final Product product1 = new Product();
        final Recipe recipe = new Recipe();
        recipe.setProducts(List.of(new Product()));
        recipe.setId(0L);
        recipe.setName("name");
        recipe.setVegan(false);
        recipe.setVegetarian(false);
        product1.setRecipe(recipe);
        product1.setId(0L);
        product1.setName("name");
        product1.setDescription("description");
        final Optional<Product> product = Optional.of(product1);
        when(mockProdRepo.findById(0L)).thenReturn(product);

        final ProductDto result = productServiceUnderTest.getProductById(0L);

    }

    @Test
    void CheckRecordNotFoundException() {

        when(mockProdRepo.findById(0L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> productServiceUnderTest.getProductById(0L))
                .isInstanceOf(RecordNotFoundException.class);
    }

    @Test
    void ShouldUpdateProduct() {

        final Product newProduct = new Product();
        final Recipe recipe = new Recipe();
        recipe.setProducts(List.of(new Product()));
        recipe.setId(0L);
        recipe.setName("name");
        recipe.setVegan(false);
        recipe.setVegetarian(false);
        newProduct.setRecipe(recipe);
        newProduct.setId(0L);
        newProduct.setName("name");
        newProduct.setDescription("description");


        final Product product1 = new Product();
        final Recipe recipe1 = new Recipe();
        recipe1.setProducts(List.of(new Product()));
        recipe1.setId(0L);
        recipe1.setName("name");
        recipe1.setVegan(false);
        recipe1.setVegetarian(false);
        product1.setRecipe(recipe1);
        product1.setId(0L);
        product1.setName("name");
        product1.setDescription("description");
        final Optional<Product> product = Optional.of(product1);
        when(mockProdRepo.findById(0L)).thenReturn(product);


        final Product product2 = new Product();
        final Recipe recipe2 = new Recipe();
        recipe2.setProducts(List.of(new Product()));
        recipe2.setId(0L);
        recipe2.setName("name");
        recipe2.setVegan(false);
        recipe2.setVegetarian(false);
        product2.setRecipe(recipe2);
        product2.setId(0L);
        product2.setName("name");
        product2.setDescription("description");
        when(mockProdRepo.save(any(Product.class))).thenReturn(product2);

        final ProductDto result = productServiceUnderTest.updateProduct(0L, newProduct);

        verify(mockProdRepo).save(any(Product.class));
    }

    @Test
    void CheckUpdateRecordException() {
        final Product newProduct = new Product();
        final Recipe recipe = new Recipe();
        recipe.setProducts(List.of(new Product()));
        recipe.setId(0L);
        recipe.setName("name");
        recipe.setVegan(false);
        recipe.setVegetarian(false);
        newProduct.setRecipe(recipe);
        newProduct.setId(0L);
        newProduct.setName("name");
        newProduct.setDescription("description");

        when(mockProdRepo.findById(0L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> productServiceUnderTest.updateProduct(0L, newProduct))
                .isInstanceOf(UpdateRecordException.class);
    }

    @Test
    void ShouldDeleteProduct() {
        final Product product1 = new Product();
        final Recipe recipe = new Recipe();
        recipe.setProducts(List.of(new Product()));
        recipe.setId(0L);
        recipe.setName("name");
        recipe.setVegan(false);
        recipe.setVegetarian(false);
        product1.setRecipe(recipe);
        product1.setId(0L);
        product1.setName("name");
        product1.setDescription("description");
        final Optional<Product> product = Optional.of(product1);
        when(mockProdRepo.findById(0L)).thenReturn(product);

        productServiceUnderTest.deleteProduct(0L);

        verify(mockProdRepo).deleteById(0L);
    }

    @Test
    void DeleteRecordException() {
        when(mockProdRepo.findById(0L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> productServiceUnderTest.deleteProduct(0L)).isInstanceOf(DeleteRecordException.class);
    }
}
