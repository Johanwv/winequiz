package nl.wine.quiz.service.generators;

import nl.wine.quiz.dto.Option;
import nl.wine.quiz.model.Variety;
import nl.wine.quiz.model.Wine;
import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TypeQuestionGenerator extends QuestionGenerator
{
    @Override
    protected Set<Option> createOptions(List<Wine> wines, Wine answer)
    {
        Set<Option> options = new HashSet<>();

        wines.forEach(wine -> options.add(createOption(combineVarieties(wine.getVarieties()), false)));
        options.add(createOption(combineVarieties(answer.getVarieties()), true));

        return options;
    }

    @Override
    boolean isValidOptionWine(Set<Wine> optionWines, Wine answer, Wine optionWine)
    {
        return !optionWine.equals(answer) && !areSameWineTypes(answer, optionWine) && !optionWines.contains(optionWine) && optionWines.stream().noneMatch(wine -> areSameWineTypes(optionWine, wine));
    }

    private String combineVarieties(Set<Variety> varieties)
    {
        List<String> varietyOption = varieties.stream().map(variety -> variety.getWineType().getName()).collect(Collectors.toList());

        return StringUtils.join(varietyOption, ", ");
    }

    private boolean areSameWineTypes(Wine answer, Wine optionWine)
    {
        return answer.getVarieties().equals(optionWine.getVarieties());
    }

}
