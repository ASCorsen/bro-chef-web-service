package nl.broscience.Brochef.web.service.services;

import nl.broscience.Brochef.web.service.dto.GoalDto;
import nl.broscience.Brochef.web.service.exceptions.DeleteRecordException;
import nl.broscience.Brochef.web.service.exceptions.RecordNotFoundException;
import nl.broscience.Brochef.web.service.exceptions.UpdateRecordException;
import nl.broscience.Brochef.web.service.models.Goal;
import nl.broscience.Brochef.web.service.repositories.GoalRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class GoalService {
    private final GoalRepository repos;

    public GoalService(GoalRepository repos) {
        this.repos = repos;
    }

    public Long createGoal(GoalDto goalDto) {
        Goal newGoal = new Goal();

        newGoal.setName(goalDto.name);
        newGoal.setDescription(goalDto.description);
        newGoal.setDiet(goalDto.diet);

        Goal savedGoal = repos.save(newGoal);
        return newGoal.getId();
    }
    public Iterable<GoalDto> getAllGoals() {
        Iterable<Goal> goalList = repos.findAll();
        ArrayList<GoalDto> goalDtoList = new ArrayList<>();

        for (Goal goal : goalList) {
            GoalDto newGoalDto = new GoalDto(goal);
            goalDtoList.add(newGoalDto);
        }
        return goalDtoList;
    }

    public void deleteGoal(Long id) {
        if (repos.findById(id).isPresent()) {
            repos.deleteById(id);
        } else {
            throw new DeleteRecordException("No Goal found with this ID");
        }
    }
    public GoalDto getGoalDtoById(Long id) {
        if (repos.findById(id).isPresent()) {
            Goal goal   = repos.findById(id).get();
            GoalDto newGoalDto = new GoalDto(goal);
            return newGoalDto;
        } else {
            throw new RecordNotFoundException("No Goal found with this ID");
        }
    }
    public GoalDto updateGoal(Long id, Goal newGoal) {

        if(repos.findById(id).isPresent()) {
            newGoal.setId(id);
            repos.save(newGoal);
            return new GoalDto(newGoal);
        }
        else {
            throw new UpdateRecordException("No Goal found with this ID");
        }
    }


}
