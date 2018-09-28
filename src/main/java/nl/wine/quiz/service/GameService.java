package nl.wine.quiz.service;

import nl.wine.quiz.dto.MultipleChoiceQuestion;
import nl.wine.quiz.model.enums.GameChoice;

import java.io.Serializable;
import java.util.List;

public interface GameService extends Serializable
{
    List<MultipleChoiceQuestion> getQuestions(GameChoice gameChoice);
}
