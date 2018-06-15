package nl.wine.quiz.dto;

import java.io.Serializable;

public class Option implements Serializable
{
    private String option;

    private boolean answer;

    public String getOption()
    {
        return option;
    }

    public void setOption(String option)
    {
        this.option = option;
    }

    public boolean isAnswer()
    {
        return answer;
    }

    public void setAnswer(boolean answer)
    {
        this.answer = answer;
    }
}
