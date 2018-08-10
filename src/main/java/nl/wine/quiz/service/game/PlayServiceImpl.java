package nl.wine.quiz.service.game;

import nl.wine.quiz.dto.MultipleChoiceQuestion;
import nl.wine.quiz.dto.Option;
import nl.wine.quiz.service.PlayService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlayServiceImpl implements PlayService
{
    public int isCorrect(String choice, MultipleChoiceQuestion multipleChoiceQuestion)
    {
        Optional<String> answer = determineAnswer(multipleChoiceQuestion);
        if (answer.isPresent() && answer.get().equals(choice))
        {
            return 1;
        }
        return 0;
    }

    public boolean isAnotherQuestion(int counter, List<MultipleChoiceQuestion> questions)
    {
        return counter < questions.size();
    }


    private Optional<String> determineAnswer(MultipleChoiceQuestion multipleChoiceQuestion)
    {
        List<Option> options = makeListOfOptions(multipleChoiceQuestion);
        return options.stream().filter(Option::isAnswer).map(Option::getChoice).findFirst();
    }

    private List<Option> makeListOfOptions(MultipleChoiceQuestion multipleChoiceQuestion)
    {
        List<Option> options = new ArrayList<>();
        options.add(multipleChoiceQuestion.getOptionA());
        options.add(multipleChoiceQuestion.getOptionB());
        options.add(multipleChoiceQuestion.getOptionC());
        options.add(multipleChoiceQuestion.getOptionD());
        return options;
    }
}
