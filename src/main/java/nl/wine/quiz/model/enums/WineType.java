package nl.wine.quiz.model.enums;

public enum WineType implements IName
{
    RED("Red"),

    WHITE("White"),

    ROSE("Rosé"),

    SPARKLING("Sparkling");

    private String name;

    WineType(String name)
    {
        this.name = name;
    }

    @Override
    public String getName()
    {
        return name;
    }
}
