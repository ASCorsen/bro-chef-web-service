package nl.broscience.Brochef.web.service.dto;

import nl.broscience.Brochef.web.service.models.Diet;
import nl.broscience.Brochef.web.service.models.Goal;
import javax.validation.constraints.NotBlank;


public class GoalDto {
    @NotBlank(message = "Please specify Goal: 'Build Muscle','Conditioning', 'LoseWeight'")
    public String name;
    @NotBlank(message = "Empty field! Describe the goal: gain more strength, gain muscle, more time spend in gym")
    public String description;

    public Diet diet;

    public GoalDto() {
    }

    public GoalDto(Goal goal) {
        this.name = goal.getName();
        this.description = goal.getDescription();
        this.diet = goal.getDiet();
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

    public Diet getDiet() {
        return diet;
    }

    public void setDiet(Diet diet) {
        this.diet = diet;
    }
}
