package nl.broscience.Brochef.web.service.dto;

import nl.broscience.Brochef.web.service.models.Product;

import java.util.List;

public class RecipeDto {
    public String name;
    public boolean isVegan;
    public boolean isVegetarian;
    public List<Product> products;
}
