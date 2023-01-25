package nl.broscience.Brochef.web.service.services;

import nl.broscience.Brochef.web.service.dto.GoalDto;
import nl.broscience.Brochef.web.service.dto.RecipeDto;
import nl.broscience.Brochef.web.service.exceptions.RecordNotFoundException;
import nl.broscience.Brochef.web.service.models.Goal;
import nl.broscience.Brochef.web.service.models.Recipe;
import nl.broscience.Brochef.web.service.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RecipeService {
    private final RecipeRepository repos;

    public RecipeService(RecipeRepository repos) {
        this.repos = repos;
    }

    public Long createRecipe(RecipeDto recipeDto) {
        Recipe newRecipe = new Recipe();


        newRecipe.setName(recipeDto.name);
        newRecipe.setVegan(recipeDto.isVegan);
        newRecipe.setVegetarian(recipeDto.isVegetarian);
        newRecipe.setProducts(recipeDto.products);


        Recipe savedRecipe = repos.save(newRecipe);
        return newRecipe.getId();
    }

    public Iterable<RecipeDto> getAllRecipes() {
        Iterable<Recipe> recipeList = repos.findAll();
        ArrayList<RecipeDto> recipeDtoList = new ArrayList<>();

        for (Recipe recipe : recipeList) {
            RecipeDto newRecipeDto = new RecipeDto(recipe);
            recipeDtoList.add(newRecipeDto);
        }
        return recipeDtoList;
    }
    public RecipeDto getRecipeById(Long id) {
        if (repos.findById(id).isPresent()) {
            Recipe recipe   = repos.findById(id).get();
            RecipeDto newRecipeDto = new RecipeDto(recipe);
            return newRecipeDto;
        } else {
            throw new RecordNotFoundException("No Recipe found with this ID");
        }
    }
    public void deleteRecipe(Long id) {
        if (repos.findById(id).isPresent()) {
            repos.deleteById(id);
        } else {
            throw new RecordNotFoundException("No Recipe found with this ID");
        }
    }
    public RecipeDto updateRecipe(Long id, Recipe newRecipe) {

        if(repos.findById(id).isPresent()) {
            newRecipe.setId(id);
            repos.save(newRecipe);
            return new RecipeDto(newRecipe);
        }
        else {
            throw new RecordNotFoundException("No Recipe found with this ID");
        }
    }



}
