package nl.broscience.Brochef.web.service.dto;

import nl.broscience.Brochef.web.service.models.Product;
import nl.broscience.Brochef.web.service.models.Recipe;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class RecipeDto {

    @NotBlank(message ="Please insert a name for the Recipe" )
    public String name;
    public boolean isVegan;
    public boolean isVegetarian;
    public List<Product> products;

    public RecipeDto() {
    }

    public RecipeDto(Recipe recipe) {
        this.name = recipe.getName();
        this.isVegan = recipe.isVegan();
        this.isVegetarian = recipe.isVegetarian();
        this.products = recipe.getProducts();
    }
}
