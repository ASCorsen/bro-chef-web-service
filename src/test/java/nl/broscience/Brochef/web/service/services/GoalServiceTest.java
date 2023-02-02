package nl.broscience.Brochef.web.service.services;

import nl.broscience.Brochef.web.service.dto.GoalDto;
import nl.broscience.Brochef.web.service.exceptions.DeleteRecordException;
import nl.broscience.Brochef.web.service.exceptions.RecordNotFoundException;
import nl.broscience.Brochef.web.service.exceptions.UpdateRecordException;
import nl.broscience.Brochef.web.service.models.Diet;
import nl.broscience.Brochef.web.service.models.Goal;
import nl.broscience.Brochef.web.service.repositories.GoalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class GoalServiceTest {

    @Mock
    private GoalRepository mockRepos;

    private GoalService goalServiceUnderTest;

    @BeforeEach
    void setUp() {
        goalServiceUnderTest = new GoalService(mockRepos);
    }

    @Test
    void shouldCreateGoal() {
        final Goal goal = new Goal();
        final Diet diet = new Diet();
        diet.setId(0L);
        diet.setName("name");
        diet.setDescription("description");
        goal.setDiet(diet);
        goal.setName("name");
        goal.setDescription("description");
        goal.setId(0L);
        final GoalDto goalDto = new GoalDto(goal);

        final Goal goal1 = new Goal();
        final Diet diet1 = new Diet();
        diet1.setId(0L);
        diet1.setName("name");
        diet1.setDescription("description");
        goal1.setDiet(diet1);
        goal1.setName("name");
        goal1.setDescription("description");
        goal1.setId(null);
        when(mockRepos.save(any(Goal.class))).thenReturn(goal1);

        final Long result = goalServiceUnderTest.createGoal(goalDto);

        assertThat(result).isEqualTo(goal1.getId());
    }

    @Test
    void shouldGetAllGoals() {
        final Goal goal = new Goal();
        final Diet diet = new Diet();
        diet.setId(0L);
        diet.setName("name");
        diet.setDescription("description");
        goal.setDiet(diet);
        goal.setName("name");
        goal.setDescription("description");
        goal.setId(0L);
        final Iterable<Goal> goals = List.of(goal);
        when(mockRepos.findAll()).thenReturn(goals);

        final Iterable<GoalDto> result = goalServiceUnderTest.getAllGoals();

    }

    @Test
    void checkGoalRepo() {
        when(mockRepos.findAll()).thenReturn(Collections.emptyList());

        final Iterable<GoalDto> result = goalServiceUnderTest.getAllGoals();

    }

    @Test
    void shouldDeleteGoal() {
        final Goal goal1 = new Goal();
        final Diet diet = new Diet();
        diet.setId(0L);
        diet.setName("name");
        diet.setDescription("description");
        goal1.setDiet(diet);
        goal1.setName("name");
        goal1.setDescription("description");
        goal1.setId(0L);
        final Optional<Goal> goal = Optional.of(goal1);
        when(mockRepos.findById(0L)).thenReturn(goal);

        goalServiceUnderTest.deleteGoal(0L);

        verify(mockRepos).deleteById(0L);
    }

    @Test
    void checkDeleteRecordException() {
        when(mockRepos.findById(0L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> goalServiceUnderTest.deleteGoal(0L)).isInstanceOf(DeleteRecordException.class);
    }

    @Test
    void shouldGetGoalDtoById() {
        final Goal goal1 = new Goal();
        final Diet diet = new Diet();
        diet.setId(0L);
        diet.setName("name");
        diet.setDescription("description");
        goal1.setDiet(diet);
        goal1.setName("name");
        goal1.setDescription("description");
        goal1.setId(0L);
        final Optional<Goal> goal = Optional.of(goal1);
        when(mockRepos.findById(0L)).thenReturn(goal);

        final GoalDto result = goalServiceUnderTest.getGoalDtoById(0L);
    }

    @Test
    void checkRecordNotFoundException() {
        when(mockRepos.findById(0L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> goalServiceUnderTest.getGoalDtoById(0L)).isInstanceOf(RecordNotFoundException.class);
    }

    @Test
    void shouldUpdateGoal() {
        final Goal newGoal = new Goal();
        final Diet diet = new Diet();
        diet.setId(0L);
        diet.setName("name");
        diet.setDescription("description");
        newGoal.setDiet(diet);
        newGoal.setName("name");
        newGoal.setDescription("description");
        newGoal.setId(0L);

        final Goal goal1 = new Goal();
        final Diet diet1 = new Diet();
        diet1.setId(0L);
        diet1.setName("name");
        diet1.setDescription("description");
        goal1.setDiet(diet1);
        goal1.setName("name");
        goal1.setDescription("description");
        goal1.setId(0L);
        final Optional<Goal> goal = Optional.of(goal1);
        when(mockRepos.findById(0L)).thenReturn(goal);

        final Goal goal2 = new Goal();
        final Diet diet2 = new Diet();
        diet2.setId(0L);
        diet2.setName("name");
        diet2.setDescription("description");
        goal2.setDiet(diet2);
        goal2.setName("name");
        goal2.setDescription("description");
        goal2.setId(0L);
        when(mockRepos.save(any(Goal.class))).thenReturn(goal2);

        final GoalDto result = goalServiceUnderTest.updateGoal(0L, newGoal);

        verify(mockRepos).save(any(Goal.class));
    }

    @Test
    void checkUpdateRecordException() {
        final Goal newGoal = new Goal();
        final Diet diet = new Diet();
        diet.setId(0L);
        diet.setName("name");
        diet.setDescription("description");
        newGoal.setDiet(diet);
        newGoal.setName("name");
        newGoal.setDescription("description");
        newGoal.setId(0L);

        when(mockRepos.findById(0L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> goalServiceUnderTest.updateGoal(0L, newGoal))
                .isInstanceOf(UpdateRecordException.class);
    }
}
