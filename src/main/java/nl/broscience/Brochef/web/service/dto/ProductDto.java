package nl.broscience.Brochef.web.service.dto;

import nl.broscience.Brochef.web.service.models.Recipe;

import javax.validation.constraints.NotBlank;

public class ProductDto {
    @NotBlank
    public String name;
    @NotBlank
    public String description;
    public Recipe recipe;
}
