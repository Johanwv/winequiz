package nl.wine.quiz.model;

import nl.wine.quiz.model.enums.WineRegion;

import javax.persistence.*;

@Entity
public class Wine
{
    @Id
    @GeneratedValue
    private int wineId;

    @Column
    private String wineName;

    @Column
    @Enumerated(EnumType.STRING)
    private WineRegion wineRegion;

    public int getWineId()
    {
        return wineId;
    }

    public String getWineName()
    {
        return wineName;
    }

    public void setWineName(String wineName)
    {
        this.wineName = wineName;
    }

    public WineRegion getWineRegion()
    {
        return wineRegion;
    }

    public void setWineRegion(WineRegion wineRegion)
    {
        this.wineRegion = wineRegion;
    }
}
