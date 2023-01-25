package nl.broscience.Brochef.web.service.dto;

import nl.broscience.Brochef.web.service.models.Product;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class RecipeDto {
    @NotBlank
    public String name;
    public boolean isVegan;
    public boolean isVegetarian;
    public List<Product> products;
}
