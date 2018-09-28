package nl.wine.quiz.service.generators;

import nl.wine.quiz.dto.Option;
import nl.wine.quiz.model.Wine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RegionQuestionGenerator extends QuestionGenerator
{
    @Override
    protected Set<Option> createOptions(List<Wine> wines, Wine answer)
    {
        Set<Option> options = new HashSet<>();

        Set<Wine> wineOptions = randomSelectOptions(wines, answer);

        for (Wine wine : wineOptions)
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
    
    private Set<Wine> randomSelectOptions(List<Wine> allWines, Wine answer)
    {
        Random random = new Random();
        int randNumb;
        List<Wine> winesForOptions = new ArrayList<>(allWines);
        winesForOptions.remove(answer);
        Set<Wine> optionWines = new HashSet<>();

        for (int i = 0; i < 3; i = optionWines.size())
        {
            randNumb = random.nextInt(winesForOptions.size());

            Wine optionWine = winesForOptions.get(randNumb);
            if (isValidOptionWine(optionWines, answer, optionWine))
            {
                optionWines.add(optionWine);
                winesForOptions.remove(optionWine);
            }
        }
        return optionWines;
    }

    private boolean isValidOptionWine(Set<Wine> optionWines, Wine answer, Wine optionWine)
    {
        return !optionWine.equals(answer) && !isSameRegion(answer, optionWine) && !optionWines.contains(optionWine) && optionWines.stream().noneMatch(wine -> isSameRegion(optionWine, wine));
    }

    private boolean isSameRegion(Wine optionWine, Wine wine)
    {
        return optionWine.getWineRegion().equals(wine.getWineRegion());
    }
}
