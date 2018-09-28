package nl.wine.quiz.model;

import nl.wine.quiz.model.enums.WineGrape;

import javax.persistence.*;
import java.util.List;

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

    @ManyToOne
    private Wine wine;

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

    public Wine getWine()
    {
        return wine;
    }

    public void setWine(Wine wines)
    {
        this.wine = wines;
    }
}
