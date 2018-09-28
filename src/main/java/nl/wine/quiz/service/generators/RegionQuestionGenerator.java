package nl.wine.quiz.service.generators;

import nl.wine.quiz.dto.Option;
import nl.wine.quiz.model.Wine;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RegionQuestionGenerator extends QuestionGenerator
{
    @Override
    protected Set<Option> createOptions(List<Wine> wines, Wine answer)
    {
        Set<Option> options = new HashSet<>();

        for (Wine wine : wines)
        {
            options.add(createOption(wine.getWineRegion().getName(), false));
        }
        options.add(createOption(answer.getWineRegion().getName(), true));
        return options;
    }

    @Override
    protected String createQuestion(Wine answer)
    {
        return answer.getWineName();
    }

    protected boolean isValidOptionWine(Set<Wine> optionWines, Wine answer, Wine optionWine)
    {
        return !optionWine.equals(answer) && !isSameRegion(answer, optionWine) && !optionWines.contains(optionWine) && optionWines.stream().noneMatch(wine -> isSameRegion(optionWine, wine));
    }

    private boolean isSameRegion(Wine optionWine, Wine wine)
    {
        return optionWine.getWineRegion().equals(wine.getWineRegion());
    }
}
