package nl.wine.quiz.service;

import nl.wine.quiz.dto.MultipleChoiceQuestion;

import java.io.Serializable;
import java.util.List;

public interface PlayService extends Serializable
{
    int isCorrect(String choice, MultipleChoiceQuestion multipleChoiceQuestion);

    boolean isAnotherQuestion(int counter, List<MultipleChoiceQuestion> questions);
}
