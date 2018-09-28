package nl.wine.quiz.model;

import nl.wine.quiz.model.enums.WineRegion;
import nl.wine.quiz.model.enums.WineType;

import javax.persistence.*;
import java.util.List;

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

    @ElementCollection
    @Column
    private List<WineType> wineTypes;

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
