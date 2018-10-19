package nl.wine.quiz.service.game;

import nl.wine.quiz.dto.MultipleChoiceQuestion;
import nl.wine.quiz.model.enums.OptionId;
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

        Assert.assertTrue(playService.isCorrect(multipleChoiceQuestion, OptionId.OPTION_A));
        Assert.assertFalse(playService.isCorrect(multipleChoiceQuestion, OptionId.OPTION_B));
        Assert.assertFalse(playService.isCorrect(multipleChoiceQuestion, OptionId.OPTION_C));
        Assert.assertFalse(playService.isCorrect(multipleChoiceQuestion, OptionId.OPTION_D));
    }


    @Test
    public void isRightAnswerCorrectTest()
    {
        MultipleChoiceQuestion multipleChoiceQuestion = MultipleChoiceQuestionFactory.createMultipleChoiceQuestionAnswer(OptionId.OPTION_A);

        Assert.assertTrue(playService.isCorrect(multipleChoiceQuestion, OptionId.OPTION_A));
    }

    @Test
    public void isWrongAnswerIncorrectTest()
    {
        MultipleChoiceQuestion multipleChoiceQuestion = MultipleChoiceQuestionFactory.createMultipleChoiceQuestionWrongOption(OptionId.OPTION_B);

        Assert.assertFalse(playService.isCorrect(multipleChoiceQuestion, OptionId.OPTION_B));
    }

}