package nl.wine.quiz.service.generators;

import nl.wine.quiz.dto.Option;
import nl.wine.quiz.model.Wine;

import java.util.List;
import java.util.Set;

public class TypeQuestionGenerator extends QuestionGenerator
{
    @Override
    protected Set<Option> createOptions(List<Wine> wines, Wine answer)
    {
        return null;
    }

    @Override
    protected String createQuestion(Wine answer)
    {
        return null;
    }

    @Override
    protected boolean isValidOptionWine(Set<Wine> optionWines, Wine answer, Wine optionWine)
    {
        return true;
    }
}
