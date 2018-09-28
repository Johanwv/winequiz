package nl.wine.quiz.service.generators;

import nl.wine.quiz.dto.Option;
import nl.wine.quiz.model.Wine;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TypeQuestionGenerator extends QuestionGenerator
{
    @Override
    protected Set<Option> createOptions(List<Wine> wines, Wine answer)
    {
        Set<Option> options = new HashSet<>();

        wines.forEach(wine ->
                wine.getVarieties().forEach(variety -> options.add(createOption(variety.getWineType().getName(), false))));
        options.add(createOption(answer.getWineRegion().getName(), true));

        return options;
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
