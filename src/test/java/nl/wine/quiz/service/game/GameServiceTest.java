package nl.wine.quiz.service.game;

import nl.wine.quiz.dto.MultipleChoiceQuestion;
import nl.wine.quiz.dto.Option;
import nl.wine.quiz.model.Wine;
import nl.wine.quiz.util.WineUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GameServiceTest
{

    private List<Wine> wines;

    private List<MultipleChoiceQuestion> questions;

    @Before
    public void setUp()
    {
        GameServiceImpl gameService = new GameServiceImpl();
        wines = WineUtil.createWines();
        questions = gameService.createQuestions(wines);
    }

    @Test
    public void testSameNumberOfQuestionsAsWines()
    {
        Assert.assertEquals(wines.size(), questions.size());
    }

    @Test
    public void testAllQuestionsAreUnique()
    {
        Assert.assertEquals(questions.stream().distinct().count(), questions.size());
    }

    @Test
    public void testAllOptionsAreUnique()
    {
        List<Option> options;
        for (MultipleChoiceQuestion question : questions)
        {
            options = new ArrayList<>();

            Option optionA = question.getOptionA();
            Option optionB = question.getOptionB();
            Option optionC = question.getOptionC();
            Option optionD = question.getOptionD();

            options.add(optionA);
            options.add(optionB);
            options.add(optionC);
            options.add(optionD);

            Assert.assertEquals(4, options.stream().distinct().count());

            final List<Option> copyOptions = new ArrayList<>(options);
            options.forEach(o -> Assert.assertTrue(copyOptions.stream().noneMatch(opt -> areSameChoices(o, opt))));
        }


    }

    private boolean areSameChoices(Option o, Option op)
    {
        return !o.equals(op) && o.getChoice().equals(op.getChoice());
    }

}