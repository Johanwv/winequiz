package nl.wine.quiz.service.game;

import nl.wine.quiz.dto.MultipleChoiceQuestion;
import nl.wine.quiz.model.Wine;
import nl.wine.quiz.model.enums.GameChoice;
import nl.wine.quiz.service.GameService;
import nl.wine.quiz.service.factory.GameFactory;
import nl.wine.quiz.service.generators.QuestionGenerator;
import nl.wine.quiz.service.hibernate.HibernateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImpl implements GameService
{
    @Autowired
    private HibernateService hibernateService;

    @Autowired
    private GameFactory gameFactory;

    @Override
    public List<MultipleChoiceQuestion> getQuestions(GameChoice gameChoice)
    {
        List<Wine> wines = hibernateService.getAll(Wine.class);

        QuestionGenerator questionGenerator = gameFactory.createQuestionGenerator(gameChoice);

        return questionGenerator.createMultipleChoiceQuestions(wines);
    }

}
