package nl.broscience.Brochef.web.service.models;

import javax.persistence.*;

@Entity
@Table(name = "diets")
public class Diet {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;

    @OneToOne
    private Goal goal;

    public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
