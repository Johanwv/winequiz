package nl.wine.quiz.dto;

import java.io.Serializable;

public class Option implements Serializable
{
    private String choice;

    private boolean answer;

    public String getChoice()
    {
        return choice;
    }

    public void setChoice(String choice)
    {
        this.choice = choice;
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
