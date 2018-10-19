package nl.wine.quiz.service.game;

import nl.wine.quiz.dto.MultipleChoiceQuestion;
import nl.wine.quiz.dto.Option;
import nl.wine.quiz.model.enums.OptionId;
import nl.wine.quiz.service.PlayService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayServiceImpl implements PlayService
{
    public boolean isCorrect(MultipleChoiceQuestion multipleChoiceQuestion, OptionId choice)
    {
        Option chosenOption = getChoice(multipleChoiceQuestion, choice);

        return chosenOption.isAnswer();
    }

    private Option getChoice(MultipleChoiceQuestion multipleChoiceQuestion, OptionId choice)
    {
        switch (choice)
        {
            case OPTION_A:
                return multipleChoiceQuestion.getOptionA();
            case OPTION_B:
                return multipleChoiceQuestion.getOptionB();
            case OPTION_C:
                return multipleChoiceQuestion.getOptionC();
            case OPTION_D:
                return multipleChoiceQuestion.getOptionD();
        }
        throw new IllegalArgumentException("Invalid choice: " + choice);
    }

    public boolean isAnotherQuestion(int counter, List<MultipleChoiceQuestion> questions)
    {
        return counter < questions.size();
    }
}
