package nl.wine.quiz.service.game;

import nl.wine.quiz.dto.MultipleChoiceQuestion;
import nl.wine.quiz.model.Wine;
import nl.wine.quiz.service.GameService;
import nl.wine.quiz.service.hibernate.HibernateService;
import nl.wine.quiz.service.hibernate.HibernateServiceImpl;

import java.util.List;

public class GameServiceImpl implements GameService
{
    private HibernateService hibernateService = new HibernateServiceImpl();

    @Override
    public List<MultipleChoiceQuestion> getQuestions()
    {
        List<Wine> wines = hibernateService.getAll(Wine.class);
        return null;
    }
}
