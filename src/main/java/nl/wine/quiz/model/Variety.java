package nl.wine.quiz.model;

import nl.wine.quiz.model.enums.WineGrape;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;
import java.util.Set;

@Entity
public class Variety
{
    @Id
    @GeneratedValue
    private int varietyId;

    @Column
    private String wineTypes;

    @ElementCollection
    @Column
    private List<WineGrape> wineGrapes;

    @ManyToMany(mappedBy = "varieties")
    private Set<Wine> wines;

    public int getVarietyId()
    {
        return varietyId;
    }

    public String getWineTypes()
    {
        return wineTypes;
    }

    public void setWineTypes(String wineTypes)
    {
        this.wineTypes = wineTypes;
    }

    public List<WineGrape> getWineGrapes()
    {
        return wineGrapes;
    }

    public void setWineGrapes(List<WineGrape> wineGrapes)
    {
        this.wineGrapes = wineGrapes;
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
