package nl.wine.quiz.service;

import nl.wine.quiz.dto.MultipleChoiceQuestion;

import java.util.List;

public interface PlayService
{
    int isCorrect(String choice, MultipleChoiceQuestion multipleChoiceQuestion);

    boolean isAnotherQuestion(int counter, List<MultipleChoiceQuestion> questions);
}
