package nl.wine.quiz.service.generators;

import nl.wine.quiz.dto.MultipleChoiceQuestion;
import nl.wine.quiz.dto.Option;
import nl.wine.quiz.model.Variety;
import nl.wine.quiz.model.Wine;
import nl.wine.quiz.util.TestHelper;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

class BaseQuestionGeneratorTest
{
    List<Wine> wines;

    List<Variety> varieties;

    List<MultipleChoiceQuestion> multipleChoiceQuestions;

    public void setUp(QuestionGenerator questionGenerator)
    {
        wines = TestHelper.createWinesWithVarities();

        multipleChoiceQuestions = questionGenerator.createMultipleChoiceQuestions(wines);
    }

    void areAllUnique()
    {
        List<Option> options;
        for (MultipleChoiceQuestion question : multipleChoiceQuestions)
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
