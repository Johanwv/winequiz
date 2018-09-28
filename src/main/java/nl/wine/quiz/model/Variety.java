package nl.wine.quiz.model;

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
    private String wineTypes;

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

    public Set<Wine> getWines()
    {
        return wines;
    }

    public void setWines(Set<Wine> wine)
    {
        this.wines = wine;
    }
}
