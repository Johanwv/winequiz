package nl.wine.quiz.service.generators;

import nl.wine.quiz.dto.MultipleChoiceQuestion;
import nl.wine.quiz.dto.Option;
import nl.wine.quiz.model.Wine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public abstract class QuestionGenerator
{
    protected abstract Set<Option> createOptions(List<Wine> wines, Wine answer);

    protected abstract String createQuestion(Wine answer);

    public List<MultipleChoiceQuestion> createMultipleChoiceQuestions(List<Wine> wines)
    {
        List<MultipleChoiceQuestion> multipleChoiceQuestions = new ArrayList<>();

        Collections.shuffle(wines);

        for (Wine answer : wines)
        {
            Set<Option> options = createOptions(wines, answer);
            String question = createQuestion(answer);

            multipleChoiceQuestions.add(createMultipleChoiceQuestion(options, question));
        }
        return multipleChoiceQuestions;
    }

    protected Option createOption(String choice, boolean answer)
    {
        Option option = new Option();
        option.setChoice(choice);
        option.setAnswer(answer);

        return option;
    }

    private MultipleChoiceQuestion createMultipleChoiceQuestion(Set<Option> options, String question)
    {
        MultipleChoiceQuestion multipleChoiceQuestion = new MultipleChoiceQuestion();

        List<Option> optionList = new ArrayList<>(options);
        Collections.shuffle(optionList);

        multipleChoiceQuestion.setQuestion(question);
        multipleChoiceQuestion.setOptionA(optionList.get(0));
        multipleChoiceQuestion.setOptionB(optionList.get(1));
        multipleChoiceQuestion.setOptionC(optionList.get(2));
        multipleChoiceQuestion.setOptionD(optionList.get(3));

        return multipleChoiceQuestion;
    }
}
