package nl.wine.quiz.model;

import nl.wine.quiz.model.enums.WineRegion;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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

    @ManyToMany(cascade = {CascadeType.ALL})
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