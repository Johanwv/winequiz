package nl.wine.quiz.service;

import nl.wine.quiz.dto.MultipleChoiceQuestion;

import java.io.Serializable;
import java.util.List;

public interface PlayService extends Serializable
{
    boolean isCorrect(MultipleChoiceQuestion multipleChoiceQuestion, String choice);

    boolean isAnotherQuestion(int counter, List<MultipleChoiceQuestion> questions);
}
