package nl.wine.quiz.model;

import nl.wine.quiz.model.enums.WineType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table
public class Variety
{
    @Id
    @GeneratedValue
    private int varietyId;

    @Column
    private WineType wineType;

    @ManyToMany(mappedBy = "varieties")
    private Set<Wine> wines;

    public int getVarietyId()
    {
        return varietyId;
    }

    public WineType getWineType()
    {
        return wineType;
    }

    public void setWineType(WineType wineTypes)
    {
        this.wineType = wineTypes;
    }

    public Set<Wine> getWines()
    {
        return wines;
    }

    public void setWines(Set<Wine> wine)
    {
        this.wines = wine;
    }
}
