package nl.wine.quiz.dto;

public class Option
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
