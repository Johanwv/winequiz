package nl.wine.quiz.util;

import nl.wine.quiz.dto.MultipleChoiceQuestion;
import nl.wine.quiz.dto.Option;

public class MultipleChoiceQuestionFactory
{
    public static MultipleChoiceQuestion createCompleteMultipleChoiceQuestionAnswerOptionA()
    {
        MultipleChoiceQuestion multipleChoiceQuestion = new MultipleChoiceQuestion();

        Option answer = OptionFactory.createAnswer();
        setOption(multipleChoiceQuestion, "optionA", answer);

        Option wrongOptionB = OptionFactory.createWrongOption();
        setOption(multipleChoiceQuestion, "optionB", wrongOptionB);

        Option wrongOptionC = OptionFactory.createWrongOption();
        setOption(multipleChoiceQuestion, "optionC", wrongOptionC);

        Option wrongOptionD = OptionFactory.createWrongOption();
        setOption(multipleChoiceQuestion, "optionD", wrongOptionD);

        return multipleChoiceQuestion;
    }

    public static MultipleChoiceQuestion createMultipleChoiceQuestionAnswer(String answerOption)
    {
        MultipleChoiceQuestion multipleChoiceQuestion = new MultipleChoiceQuestion();

        Option answer = OptionFactory.createAnswer();
        setOption(multipleChoiceQuestion, answerOption, answer);

        return multipleChoiceQuestion;
    }

    public static MultipleChoiceQuestion createMultipleChoiceQuestionWrongOption(String answerOption)
    {
        MultipleChoiceQuestion multipleChoiceQuestion = new MultipleChoiceQuestion();

        Option wrongOption = OptionFactory.createWrongOption();
        setOption(multipleChoiceQuestion, answerOption, wrongOption);

        return multipleChoiceQuestion;
    }

    private static void setOption(MultipleChoiceQuestion multipleChoiceQuestion, String answerOption, Option option)
    {
        switch (answerOption)
        {
            case "optionA":
                multipleChoiceQuestion.setOptionA(option);
                break;
            case "optionB":
                multipleChoiceQuestion.setOptionB(option);
                break;
            case "optionC":
                multipleChoiceQuestion.setOptionC(option);
                break;
            case "optionD":
                multipleChoiceQuestion.setOptionD(option);
                break;
        }
    }

}
