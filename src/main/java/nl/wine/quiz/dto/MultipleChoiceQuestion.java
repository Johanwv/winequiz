package nl.wine.quiz.dto;

import java.io.Serializable;

public class MultipleChoiceQuestion implements Serializable
{
    private String question;

    private Option optionA;

    private Option optionB;

    private Option optionC;

    private Option optionD;

    public String getQuestion()
    {
        return question;
    }

    public void setQuestion(String question)
    {
        this.question = question;
    }

    public Option getOptionA()
    {
        return optionA;
    }

    public void setOptionA(Option optionA)
    {
        this.optionA = optionA;
    }

    public Option getOptionB()
    {
        return optionB;
    }

    public void setOptionB(Option optionB)
    {
        this.optionB = optionB;
    }

    public Option getOptionC()
    {
        return optionC;
    }

    public void setOptionC(Option optionC)
    {
        this.optionC = optionC;
    }

    public Option getOptionD()
    {
        return optionD;
    }

    public void setOptionD(Option optionD)
    {
        this.optionD = optionD;
    }

}
