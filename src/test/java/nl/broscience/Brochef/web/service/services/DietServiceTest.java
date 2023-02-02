package nl.broscience.Brochef.web.service.services;

import nl.broscience.Brochef.web.service.dto.DietDto;
import nl.broscience.Brochef.web.service.exceptions.DeleteRecordException;
import nl.broscience.Brochef.web.service.exceptions.NoRelatedObjectFoundException;
import nl.broscience.Brochef.web.service.exceptions.RecordNotFoundException;
import nl.broscience.Brochef.web.service.exceptions.UpdateRecordException;
import nl.broscience.Brochef.web.service.models.Diet;
import nl.broscience.Brochef.web.service.models.Goal;
import nl.broscience.Brochef.web.service.repositories.DietRepository;
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
class DietServiceTest {

    @Mock
    private DietRepository mockRepos;
    @Mock
    private GoalRepository mockGoalRepos;

    private DietService dietServiceUnderTest;

    @BeforeEach
    void setUp() {
        dietServiceUnderTest = new DietService(mockRepos, mockGoalRepos);
    }

    @Test
    void ShouldCreateDiet() {

        final Goal goal = new Goal();
        final Diet diet = new Diet();
        diet.setId(0L);
        diet.setName("name");
        diet.setDescription("description");
        goal.setDiet(diet);
        goal.setName("name");
        goal.setDescription("description");
        goal.setId(0L);
        final DietDto dietDto = new DietDto("name", "description", goal);

        final Goal goal2 = new Goal();
        final Diet diet1 = new Diet();
        diet1.setId(0L);
        diet1.setName("name");
        diet1.setDescription("description");
        goal2.setDiet(diet1);
        goal2.setName("name");
        goal2.setDescription("description");
        goal2.setId(0L);
        final Optional<Goal> goal1 = Optional.of(goal2);
        when(mockGoalRepos.findById(0L)).thenReturn(goal1);


        final Diet diet2 = new Diet();
        final Goal goal3 = new Goal();
        goal3.setName("name");
        goal3.setDescription("description");
        goal3.setId(0L);
        diet2.setGoal(goal3);
        diet2.setId(0L);
        diet2.setName("name");
        diet2.setDescription("description");
        when(mockRepos.save(any(Diet.class))).thenReturn(diet2);

        final Long result = dietServiceUnderTest.createDiet(dietDto, 0L);

        assertThat(result).isEqualTo(0L);
    }

    @Test
    void ShouldCheckDietGoal() {

        final Goal goal = new Goal();
        final Diet diet = new Diet();
        diet.setId(0L);
        diet.setName("name");
        diet.setDescription("description");
        goal.setDiet(diet);
        goal.setName("name");
        goal.setDescription("description");
        goal.setId(0L);
        final DietDto dietDto = new DietDto("name", "description", goal);
        when(mockGoalRepos.findById(0L)).thenReturn(Optional.empty());


        assertThatThrownBy(() -> dietServiceUnderTest.createDiet(dietDto, 0L))
                .isInstanceOf(NoRelatedObjectFoundException.class);
    }

    @Test
    void ShouldReturnGetDiet() {

        final Diet diet = new Diet();
        final Goal goal = new Goal();
        goal.setName("name");
        goal.setDescription("description");
        goal.setId(0L);
        diet.setGoal(goal);
        diet.setId(0L);
        diet.setName("name");
        diet.setDescription("description");
        final Iterable<Diet> diets = List.of(diet);
        when(mockRepos.findAll()).thenReturn(diets);

        final Iterable<DietDto> result = dietServiceUnderTest.getAllDiet();

    }

    @Test
    void CheckDietRepoNoItems() {

        when(mockRepos.findAll()).thenReturn(Collections.emptyList());

        final Iterable<DietDto> result = dietServiceUnderTest.getAllDiet();

    }

    @Test
    void ShouldReturnDietById() {

        final Diet diet1 = new Diet();
        final Goal goal = new Goal();
        goal.setName("name");
        goal.setDescription("description");
        goal.setId(0L);
        diet1.setGoal(goal);
        diet1.setId(0L);
        diet1.setName("name");
        diet1.setDescription("description");
        final Optional<Diet> diet = Optional.of(diet1);
        when(mockRepos.findById(0L)).thenReturn(diet);

        final DietDto result = dietServiceUnderTest.getDietById(0L);

    }

    @Test
    void ShouldCheckRecordNotFoundByID() {

        when(mockRepos.findById(0L)).thenReturn(Optional.empty());


        assertThatThrownBy(() -> dietServiceUnderTest.getDietById(0L)).isInstanceOf(RecordNotFoundException.class);
    }

    @Test
    void ShouldDeleteDiet() {

        final Diet diet1 = new Diet();
        final Goal goal = new Goal();
        goal.setName("name");
        goal.setDescription("description");
        goal.setId(0L);
        diet1.setGoal(goal);
        diet1.setId(0L);
        diet1.setName("name");
        diet1.setDescription("description");
        final Optional<Diet> diet = Optional.of(diet1);
        when(mockRepos.findById(0L)).thenReturn(diet);

        dietServiceUnderTest.deleteDiet(0L);

        verify(mockRepos).deleteById(0L);
    }

    @Test
    void CheckDeleteException() {

        when(mockRepos.findById(0L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> dietServiceUnderTest.deleteDiet(0L)).isInstanceOf(DeleteRecordException.class);
    }

    @Test
    void ShouldUpdateDiet() {

        final Diet newDiet = new Diet();
        final Goal goal = new Goal();
        goal.setName("name");
        goal.setDescription("description");
        goal.setId(0L);
        newDiet.setGoal(goal);
        newDiet.setId(0L);
        newDiet.setName("name");
        newDiet.setDescription("description");

        final Diet diet1 = new Diet();
        final Goal goal1 = new Goal();
        goal1.setName("name");
        goal1.setDescription("description");
        goal1.setId(0L);
        diet1.setGoal(goal1);
        diet1.setId(0L);
        diet1.setName("name");
        diet1.setDescription("description");
        final Optional<Diet> diet = Optional.of(diet1);
        when(mockRepos.findById(0L)).thenReturn(diet);


        final Diet diet2 = new Diet();
        final Goal goal2 = new Goal();
        goal2.setName("name");
        goal2.setDescription("description");
        goal2.setId(0L);
        diet2.setGoal(goal2);
        diet2.setId(0L);
        diet2.setName("name");
        diet2.setDescription("description");
        when(mockRepos.save(any(Diet.class))).thenReturn(diet2);

        final DietDto result = dietServiceUnderTest.updateDiet(0L, newDiet);

        verify(mockRepos).save(any(Diet.class));
    }

    @Test
    void ShouldCheckExceptionUpdateDiet() {
        final Diet newDiet = new Diet();
        final Goal goal = new Goal();
        goal.setName("name");
        goal.setDescription("description");
        goal.setId(0L);
        newDiet.setGoal(goal);
        newDiet.setId(0L);
        newDiet.setName("name");
        newDiet.setDescription("description");

        when(mockRepos.findById(0L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> dietServiceUnderTest.updateDiet(0L, newDiet))
                .isInstanceOf(UpdateRecordException.class);
    }
}
