package nl.wine.quiz.dto;

import java.io.Serializable;
import java.util.List;

public class MultipleChoiceQuestions implements Serializable
{
    private String question;

    private List<Option> options;

    public String getQuestion()
    {
        return question;
    }

    public void setQuestion(String question)
    {
        this.question = question;
    }

    public List<Option> getOptions()
    {
        return options;
    }

    public void setOptions(List<Option> options)
    {
        this.options = options;
    }
}