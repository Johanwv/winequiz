package nl.wine.quiz.service;

import nl.wine.quiz.dto.MultipleChoiceQuestion;

import java.util.List;

public interface GameService
{
    List<MultipleChoiceQuestion> getQuestions();
}
