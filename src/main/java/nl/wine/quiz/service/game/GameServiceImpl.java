package nl.wine.quiz.service.game;

import nl.wine.quiz.dto.MultipleChoiceQuestion;
import nl.wine.quiz.dto.Option;
import nl.wine.quiz.model.Wine;
import nl.wine.quiz.model.enums.GameChoice;
import nl.wine.quiz.service.GameService;
import nl.wine.quiz.service.hibernate.HibernateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

@Service
public class GameServiceImpl implements GameService
{
    @Autowired
    private HibernateService hibernateService;

    @Override
    public List<MultipleChoiceQuestion> getQuestions(GameChoice gameChoice)
    {
        List<Wine> wines = hibernateService.getAll(Wine.class);
        if (gameChoice.equals(GameChoice.REGION))
        {
            return createQuestions(wines);
        }
        else if (gameChoice.equals(GameChoice.TYPE))
        {

        }
        else if (gameChoice.equals(GameChoice.GRAPE))
        {

        }
        throw new IllegalArgumentException("Game choice does not exist: " + gameChoice);
    }

    List<MultipleChoiceQuestion> createQuestions(List<Wine> wines)
    {
        List<MultipleChoiceQuestion> multipleChoiceQuestions = new ArrayList<>();

        Collections.shuffle(wines);

        for (Wine answer : wines)
        {
            List<Wine> winesForQuestion = createWinesForQuestion(wines, answer);

            multipleChoiceQuestions.add(createQuestion(winesForQuestion, answer));
        }
        return multipleChoiceQuestions;
    }

    private List<Wine> createWinesForQuestion(List<Wine> allWines, Wine answer)
    {
        List<Wine> winesForQuestion = new ArrayList<>();
        winesForQuestion.add(answer);
        winesForQuestion.addAll(randomSelectOptions(allWines, answer));
        Collections.shuffle(winesForQuestion);

        return winesForQuestion;
    }

    private HashSet<Wine> randomSelectOptions(List<Wine> allWines, Wine answer)
    {
        Random random = new Random();
        int randNumb;
        List<Wine> winesForOptions = new ArrayList<>(allWines);
        winesForOptions.remove(answer);
        HashSet<Wine> optionWines = new HashSet<>();

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

    private MultipleChoiceQuestion createQuestion(List<Wine> winesForQuestion, Wine answer)
    {
        MultipleChoiceQuestion newQuestion = new MultipleChoiceQuestion();
        newQuestion.setQuestion(answer.getWineName());
        for (Wine wine : winesForQuestion)
        {
            Option option = createOption(answer, wine);
            setOptionForQuestion(newQuestion, option);
        }
        return newQuestion;
    }

    private Option createOption(Wine answer, Wine wine)
    {
        Option option = new Option();
        option.setChoice(wine.getWineRegion().getName());
        option.setAnswer(wine.equals(answer));

        return option;
    }

    private boolean isValidOptionWine(HashSet<Wine> optionWines, Wine answer, Wine optionWine)
    {
        return !optionWine.equals(answer) && !isSameRegion(answer, optionWine) && !optionWines.contains(optionWine) && optionWines.stream().noneMatch(wine -> isSameRegion(optionWine, wine));
    }

    private boolean isSameRegion(Wine optionWine, Wine wine)
    {
        return optionWine.getWineRegion().equals(wine.getWineRegion());
    }

    private void setOptionForQuestion(MultipleChoiceQuestion question, Option option)
    {
        if (question.getOptionA() == null)
        {
            question.setOptionA(option);
        }
        else if (question.getOptionB() == null)
        {
            question.setOptionB(option);
        }
        else if (question.getOptionC() == null)
        {
            question.setOptionC(option);
        }
        else if (question.getOptionD() == null)
        {
            question.setOptionD(option);
        }
        else
        {
            throw new IllegalStateException("No options to set for question: " + question);
        }
    }
}
