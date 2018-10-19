package nl.wine.quiz.model.enums;

public enum OptionId implements IName
{
    OPTION_A("optionA"),

    OPTION_B("optionB"),

    OPTION_C("optionC"),

    OPTION_D("optionD");

    private String name;

    OptionId(String name)
    {
        this.name = name;
    }

    public static OptionId getOptionId(String option)
    {
        if (OPTION_A.getName().equals(option))
        {
            return OPTION_A;
        }
        else if (OPTION_B.getName().equals(option))
        {
            return OPTION_B;
        }
        else if (OPTION_C.getName().equals(option))
        {
            return OPTION_C;
        }
        else if (OPTION_D.getName().equals(option))
        {
            return OPTION_D;
        }
        throw new IllegalArgumentException("Not a valid option: " + option);
    }

    @Override
    public String getName()
    {
        return name;
    }
}
