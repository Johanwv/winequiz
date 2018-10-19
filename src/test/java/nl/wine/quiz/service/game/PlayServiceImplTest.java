package nl.wine.quiz.service.game;

import nl.wine.quiz.dto.MultipleChoiceQuestion;
import nl.wine.quiz.service.PlayService;
import nl.wine.quiz.util.MultipleChoiceQuestionFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PlayServiceImplTest
{
    private PlayService playService;

    @Before
    public void setUp()
    {
        playService = new PlayServiceImpl();
    }

    @Test
    public void validateAllOptionsTest()
    {
        MultipleChoiceQuestion multipleChoiceQuestion = MultipleChoiceQuestionFactory.createCompleteMultipleChoiceQuestionAnswerOptionA();

        Assert.assertTrue(playService.isCorrect(multipleChoiceQuestion, "optionA"));
        Assert.assertFalse(playService.isCorrect(multipleChoiceQuestion, "optionB"));
        Assert.assertFalse(playService.isCorrect(multipleChoiceQuestion, "optionC"));
        Assert.assertFalse(playService.isCorrect(multipleChoiceQuestion, "optionD"));
    }


    @Test
    public void isRightAnswerCorrectTest()
    {
        MultipleChoiceQuestion multipleChoiceQuestion = MultipleChoiceQuestionFactory.createMultipleChoiceQuestionAnswer("optionA");

        Assert.assertTrue(playService.isCorrect(multipleChoiceQuestion, "optionA"));
    }

    @Test
    public void isWrongAnswerIncorrectTest()
    {
        MultipleChoiceQuestion multipleChoiceQuestion = MultipleChoiceQuestionFactory.createMultipleChoiceQuestionWrongOption("optionB");

        Assert.assertFalse(playService.isCorrect(multipleChoiceQuestion, "optionB"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateExceptionTest()
    {
        MultipleChoiceQuestion multipleChoiceQuestion = MultipleChoiceQuestionFactory.createMultipleChoiceQuestionAnswer("optionA");

        Assert.assertTrue(playService.isCorrect(multipleChoiceQuestion, "optionE"));
    }

}