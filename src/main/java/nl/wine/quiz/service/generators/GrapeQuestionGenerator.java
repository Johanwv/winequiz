package nl.wine.quiz.service.generators;

import nl.wine.quiz.dto.Option;
import nl.wine.quiz.model.Wine;

import java.util.List;
import java.util.Set;

public class GrapeQuestionGenerator extends QuestionGenerator
{
    @Override
    protected Set<Option> createOptions(List<Wine> wines, Wine answer)
    {
        return null;
    }

    @Override
    boolean isValidOptionWine(Set<Wine> optionWines, Wine answer, Wine optionWine)
    {
        return false;
    }
}
