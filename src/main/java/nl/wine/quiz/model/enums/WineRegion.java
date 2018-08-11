package nl.wine.quiz.model.enums;

public enum WineRegion implements IName
{
    VALLEE_DE_LA_LOIRE("Vallée de la Loire"),

    BOURGOGNE("Bourgogne"),

    BORDEAUX("Bordeaux"),

    BEAUJOLAIS("Beaujolais"),

    ALSACE("Alsace"),

    VALLEE_DU_RHONE("Vallée du Rhône");

    private String name;

    WineRegion(String name)
    {
        this.name = name;
    }

    @Override
    public String getName()
    {
        return name;
    }

}
