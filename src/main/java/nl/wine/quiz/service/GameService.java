package nl.wine.quiz.service;

import nl.wine.quiz.dto.MultipleChoiceQuestion;

import java.io.Serializable;
import java.util.List;

public interface GameService extends Serializable
{
    List<MultipleChoiceQuestion> getQuestions();
}
