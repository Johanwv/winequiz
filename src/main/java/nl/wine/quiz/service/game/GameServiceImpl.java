package nl.wine.quiz.service.game;

import nl.wine.quiz.dto.MultipleChoiceQuestion;
import nl.wine.quiz.dto.Option;
import nl.wine.quiz.model.Wine;
import nl.wine.quiz.service.GameService;
import nl.wine.quiz.service.hibernate.HibernateService;
import nl.wine.quiz.service.hibernate.HibernateServiceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GameServiceImpl implements GameService
{
    private HibernateService hibernateService = new HibernateServiceImpl();

    @Override
    public List<MultipleChoiceQuestion> getQuestions()
    {
        List<Wine> wines = hibernateService.getAll(Wine.class);
        return mapWinesToQuestions(wines);
    }

    private List<MultipleChoiceQuestion> mapWinesToQuestions(List<Wine> wines)
    {
        List<MultipleChoiceQuestion> multipleChoiceQuestions = new ArrayList<>();
        Random random = new Random();
        int randNumb;

        for (int i = 0; i < wines.size(); i++)
        {
            Collections.shuffle(wines);
            randNumb = random.nextInt(4);

            MultipleChoiceQuestion question = createQuestion(wines, randNumb);
            multipleChoiceQuestions.add(question);
        }
        return multipleChoiceQuestions;
    }

    private MultipleChoiceQuestion createQuestion(List<Wine> wines, int randNumb)
    {
        MultipleChoiceQuestion newQuestion = new MultipleChoiceQuestion();
        for (int j = 0; j < 4; j++)
        {
            Option option = new Option();

            Wine wine = wines.get(j);
            option.setChoice(wine.getWineRegion().getName());

            determineAnswerAndCouple(newQuestion, option, wine, randNumb, j);
            coupleOptionWithQuestion(newQuestion, option, j);
        }
        return newQuestion;
    }

    private void determineAnswerAndCouple(MultipleChoiceQuestion question, Option option, Wine wine, int randNumb, int j)
    {
        if (randNumb == j)
        {
            option.setAnswer(true);
            question.setQuestion(wine.getWineName());
        }
        else
        {
            option.setAnswer(false);
        }
    }

    private void coupleOptionWithQuestion(MultipleChoiceQuestion question, Option option, int j)
    {
        switch (j)
        {
            case 0:
                question.setOptionA(option);
                break;
            case 1:
                question.setOptionB(option);
                break;
            case 2:
                question.setOptionC(option);
                break;
            case 3:
                question.setOptionD(option);
                break;
        }
    }
}
