package nl.wine.quiz.model;

import nl.wine.quiz.model.enums.WineRegion;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Wine
{
    @Id
    @GeneratedValue
    private int wineId;

    @Column(nullable = false)
    private String wineName;

    @Column
    @Enumerated(EnumType.STRING)
    private WineRegion wineRegion;

    @OneToMany(mappedBy = "wine")
    private Set<Variety> varieties;

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

    public Set<Variety> getVarieties()
    {
        return varieties;
    }

    public void setVarieties(Set<Variety> varieties)
    {
        this.varieties = varieties;
    }
}