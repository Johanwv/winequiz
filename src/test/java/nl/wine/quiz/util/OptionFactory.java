package nl.wine.quiz.util;

import nl.wine.quiz.dto.Option;

public class OptionFactory
{

    public static Option createAnswer()
    {
        Option option = new Option();
        option.setAnswer(true);
        return option;
    }

    public static Option createWrongOption()
    {
        Option option = new Option();
        option.setAnswer(false);
        return option;
    }
}
