package nl.wine.quiz.service.game;

import nl.wine.quiz.dto.MultipleChoiceQuestion;
import nl.wine.quiz.dto.Option;
import nl.wine.quiz.model.Wine;
import nl.wine.quiz.service.GameService;
import nl.wine.quiz.service.hibernate.HibernateService;
import nl.wine.quiz.service.hibernate.HibernateServiceImpl;

import java.util.ArrayList;
import java.util.List;

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


        for (int i = 0; i < 4; i++)
        {
            MultipleChoiceQuestion question = new MultipleChoiceQuestion();
            for (int j = 0; j < wines.size(); j++)
            {
                Option option = new Option();

                Wine wine = wines.get(j);
                option.setOption(wine.getWineRegion().getName());
                if (j == i)
                {
                    option.setAnswer(true);
                    question.setQuestion(wine.getWineName());
                } else
                {
                    option.setAnswer(false);
                }

                if (j == 0)
                {
                    question.setOptionA(option);
                } else if (j == 1)
                {
                    question.setOptionB(option);
                } else if (j == 2)
                {
                    question.setOptionC(option);
                } else if (j == 3)
                {
                    question.setOptionD(option);
                }
            }
            multipleChoiceQuestions.add(question);
        }
        return multipleChoiceQuestions;
    }
}
