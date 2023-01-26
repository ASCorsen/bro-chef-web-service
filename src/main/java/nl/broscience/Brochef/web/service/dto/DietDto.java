package nl.broscience.Brochef.web.service.dto;

import nl.broscience.Brochef.web.service.models.Diet;
import nl.broscience.Brochef.web.service.models.Goal;
import javax.validation.constraints.NotBlank;


public class DietDto {
    @NotBlank(message = "Please enter a Diet name: 'Low KCal','High Kcal','BMR Kcal'")
    public String name;
    @NotBlank(message = "Please specify diet:'Client focus on high Kcal food!'")
    public String description;

    public Goal goal;

    public DietDto(String name, String description, Goal goal) {
        this.name = name;
        this.description = description;
        this.goal = goal;
    }

    public DietDto(Diet diet) {
        this.name = diet.getName();
        this.description = diet.getDescription();
        this.goal = diet.getGoal();
    }

    public DietDto() {
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

    public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }
}
