package nl.wine.quiz.service.factory;

import nl.wine.quiz.model.enums.GameChoice;
import nl.wine.quiz.service.generators.QuestionGenerator;

public interface GameFactory
{
    QuestionGenerator createQuestionGenerator(GameChoice gameChoice);
}
