package nl.wine.quiz.service.generators;

import nl.wine.quiz.dto.MultipleChoiceQuestion;
import nl.wine.quiz.dto.Option;
import nl.wine.quiz.model.Wine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public abstract class QuestionGenerator
{
    protected abstract Set<Option> createOptions(List<Wine> wines, Wine answer);

    protected abstract String createQuestion(Wine answer);

    protected abstract boolean isValidOptionWine(Set<Wine> optionWines, Wine answer, Wine optionWine);

    public List<MultipleChoiceQuestion> createMultipleChoiceQuestions(List<Wine> wines)
    {
        List<MultipleChoiceQuestion> multipleChoiceQuestions = new ArrayList<>();

        Collections.shuffle(wines);

        for (Wine answer : wines)
        {
            Set<Wine> wineOptions = randomSelectOptions(wines, answer);

            Set<Option> options = createOptions(new ArrayList<>(wineOptions), answer);
            String question = createQuestion(answer);

            multipleChoiceQuestions.add(createMultipleChoiceQuestion(options, question));
        }
        return multipleChoiceQuestions;
    }

    Option createOption(String choice, boolean answer)
    {
        Option option = new Option();
        option.setChoice(choice);
        option.setAnswer(answer);

        return option;
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

    private MultipleChoiceQuestion createMultipleChoiceQuestion(Set<Option> options, String question)
    {
        MultipleChoiceQuestion multipleChoiceQuestion = new MultipleChoiceQuestion();

        List<Option> optionList = new ArrayList<>(options);
        Collections.shuffle(optionList);

        multipleChoiceQuestion.setQuestion(question);
        multipleChoiceQuestion.setOptionA(optionList.get(0));
        multipleChoiceQuestion.setOptionB(optionList.get(1));
        multipleChoiceQuestion.setOptionC(optionList.get(2));
        multipleChoiceQuestion.setOptionD(optionList.get(3));

        return multipleChoiceQuestion;
    }
}
