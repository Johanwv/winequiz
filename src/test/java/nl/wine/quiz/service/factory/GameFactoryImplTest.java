package nl.wine.quiz.service.factory;

import nl.wine.quiz.model.enums.GameChoice;
import nl.wine.quiz.service.generators.GrapeQuestionGenerator;
import nl.wine.quiz.service.generators.QuestionGenerator;
import nl.wine.quiz.service.generators.RegionQuestionGenerator;
import nl.wine.quiz.service.generators.TypeQuestionGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class GameFactoryImplTest
{

    private GameFactoryImpl gameFactory;

    @Before
    public void setUp()
    {
        gameFactory = new GameFactoryImpl();
    }

    @Test
    public void createRegionGeneratorTest()
    {
        QuestionGenerator questionGenerator = gameFactory.createQuestionGenerator(GameChoice.REGION);

        Assert.assertTrue(questionGenerator instanceof RegionQuestionGenerator);
    }

    @Test
    public void createGrapeGeneratorTest()
    {
        QuestionGenerator questionGenerator = gameFactory.createQuestionGenerator(GameChoice.GRAPE);

        Assert.assertTrue(questionGenerator instanceof GrapeQuestionGenerator);
    }

    @Test
    public void createTypeGeneratorTest()
    {
        QuestionGenerator questionGenerator = gameFactory.createQuestionGenerator(GameChoice.TYPE);

        Assert.assertTrue(questionGenerator instanceof TypeQuestionGenerator);
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateExceptionTest()
    {
        gameFactory.createQuestionGenerator(null);
    }

    @Test
    public void validateAllGameChoiceHaveQuestionGenerator()
    {
        List<GameChoice> gameChoices = Arrays.asList(GameChoice.values());

        gameChoices.forEach(gameFactory::createQuestionGenerator);
    }


}