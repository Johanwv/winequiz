package nl.wine.quiz.model;

import nl.wine.quiz.model.enums.WineRegion;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table
public class Wine
{
    @Id
    @GeneratedValue
    private int wineId;

    @Column(nullable = false)
    private String wineName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private WineRegion wineRegion;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "Wine_Variety",
            joinColumns = {@JoinColumn(name = "wine_id")},
            inverseJoinColumns = {@JoinColumn(name = "variety_id")}
    )
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