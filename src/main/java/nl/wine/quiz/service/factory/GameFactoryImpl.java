package nl.wine.quiz.service.factory;

import nl.wine.quiz.model.enums.GameChoice;
import nl.wine.quiz.service.generators.GrapeQuestionGenerator;
import nl.wine.quiz.service.generators.QuestionGenerator;
import nl.wine.quiz.service.generators.RegionQuestionGenerator;
import nl.wine.quiz.service.generators.TypeQuestionGenerator;
import org.springframework.stereotype.Service;

@Service
public class GameFactoryImpl implements GameFactory
{
    @Override
    public QuestionGenerator createQuestionGenerator(GameChoice gameChoice)
    {
        if (GameChoice.REGION.equals(gameChoice))
        {
            return new RegionQuestionGenerator();
        }
        else if (GameChoice.TYPE.equals(gameChoice))
        {
            return new TypeQuestionGenerator();
        }
        else if (GameChoice.GRAPE.equals(gameChoice))
        {
            return new GrapeQuestionGenerator();
        }
        throw new IllegalArgumentException("Game choice does not exist: " + gameChoice);
    }
}
