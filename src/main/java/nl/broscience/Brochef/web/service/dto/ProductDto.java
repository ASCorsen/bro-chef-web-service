package nl.broscience.Brochef.web.service.dto;

import nl.broscience.Brochef.web.service.models.Product;
import nl.broscience.Brochef.web.service.models.Recipe;
import javax.validation.constraints.NotBlank;

public class ProductDto {
    @NotBlank(message = "fill in a Product Name")
    public String name;
    @NotBlank(message = "Describe your product")
    public String description;
    public Recipe recipe;

    public ProductDto(String name, String description, Recipe recipe) {
        this.name = name;
        this.description = description;
        this.recipe = recipe;
    }

    public ProductDto(Product product) {
        this.name = product.getName();
        this.description = product.getDescription();
        this.recipe = product.getRecipe();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
