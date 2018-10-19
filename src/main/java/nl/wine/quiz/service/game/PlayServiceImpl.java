package nl.wine.quiz.service.game;

import nl.wine.quiz.dto.MultipleChoiceQuestion;
import nl.wine.quiz.dto.Option;
import nl.wine.quiz.service.PlayService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayServiceImpl implements PlayService
{
    public boolean isCorrect(MultipleChoiceQuestion multipleChoiceQuestion, String choice)
    {
        Option chosenOption = getChoice(multipleChoiceQuestion, choice);

        return chosenOption.isAnswer();
    }

    private Option getChoice(MultipleChoiceQuestion multipleChoiceQuestion, String choice)
    {
        switch (choice)
        {
            case "optionA":
                return multipleChoiceQuestion.getOptionA();
            case "optionB":
                return multipleChoiceQuestion.getOptionA();
            case "optionC":
                return multipleChoiceQuestion.getOptionA();
            case "optionD":
                return multipleChoiceQuestion.getOptionA();
            default:
                throw new IllegalArgumentException("Invalid choice: " + choice);
        }
    }

    public boolean isAnotherQuestion(int counter, List<MultipleChoiceQuestion> questions)
    {
        return counter < questions.size();
    }
}
