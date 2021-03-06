package nl.wine.quiz.service.generators;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TypeQuestionGeneratorTest extends BaseQuestionGeneratorTest
{
    @Before
    public void setUp()
    {
        super.setUp(new TypeQuestionGenerator());
    }

    @Test
    public void testSameNumberOfQuestionsAsWines()
    {
        Assert.assertEquals(wines.size(), multipleChoiceQuestions.size());
    }

    @Test
    public void testAllQuestionsAreUnique()
    {
        Assert.assertEquals(multipleChoiceQuestions.stream().distinct().count(), multipleChoiceQuestions.size());
    }

    @Test
    public void testAllOptionsAreUnique()
    {
        areAllUnique();
    }
}
