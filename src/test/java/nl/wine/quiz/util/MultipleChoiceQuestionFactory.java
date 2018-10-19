package nl.wine.quiz.util;

import nl.wine.quiz.dto.MultipleChoiceQuestion;
import nl.wine.quiz.dto.Option;
import nl.wine.quiz.model.enums.OptionId;

public class MultipleChoiceQuestionFactory
{
    public static MultipleChoiceQuestion createCompleteMultipleChoiceQuestionAnswerOptionA()
    {
        MultipleChoiceQuestion multipleChoiceQuestion = new MultipleChoiceQuestion();

        Option answer = OptionFactory.createAnswer();
        setOption(multipleChoiceQuestion, OptionId.OPTION_A, answer);

        Option wrongOptionB = OptionFactory.createWrongOption();
        setOption(multipleChoiceQuestion, OptionId.OPTION_B, wrongOptionB);

        Option wrongOptionC = OptionFactory.createWrongOption();
        setOption(multipleChoiceQuestion, OptionId.OPTION_C, wrongOptionC);

        Option wrongOptionD = OptionFactory.createWrongOption();
        setOption(multipleChoiceQuestion, OptionId.OPTION_D, wrongOptionD);

        return multipleChoiceQuestion;
    }

    public static MultipleChoiceQuestion createMultipleChoiceQuestionAnswer(OptionId answerOption)
    {
        MultipleChoiceQuestion multipleChoiceQuestion = new MultipleChoiceQuestion();

        Option answer = OptionFactory.createAnswer();
        setOption(multipleChoiceQuestion, answerOption, answer);

        return multipleChoiceQuestion;
    }

    public static MultipleChoiceQuestion createMultipleChoiceQuestionWrongOption(OptionId answerOption)
    {
        MultipleChoiceQuestion multipleChoiceQuestion = new MultipleChoiceQuestion();

        Option wrongOption = OptionFactory.createWrongOption();
        setOption(multipleChoiceQuestion, answerOption, wrongOption);

        return multipleChoiceQuestion;
    }

    private static void setOption(MultipleChoiceQuestion multipleChoiceQuestion, OptionId answerOption, Option option)
    {
        switch (answerOption)
        {
            case OPTION_A:
                multipleChoiceQuestion.setOptionA(option);
                break;
            case OPTION_B:
                multipleChoiceQuestion.setOptionB(option);
                break;
            case OPTION_C:
                multipleChoiceQuestion.setOptionC(option);
                break;
            case OPTION_D:
                multipleChoiceQuestion.setOptionD(option);
                break;
        }
    }

}
