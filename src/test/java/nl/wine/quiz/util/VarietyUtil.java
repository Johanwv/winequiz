package nl.wine.quiz.util;

import nl.wine.quiz.model.Variety;
import nl.wine.quiz.model.enums.WineType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class VarietyUtil
{
    static Variety createVarietie(WineType wineType)
    {
        Variety variety = new Variety();
        variety.setWineType(wineType);
        return variety;
    }

    public static Variety createVarietie(String wineType)
    {
        Variety variety = new Variety();
        variety.setWineType(getWineType(wineType));
        return variety;
    }

    private static WineType getWineType(String wineType)
    {
        List<WineType> wineTypes = new ArrayList<>(Arrays.asList(WineType.class.getEnumConstants()));

        Optional<WineType> wineRegionOptional = wineTypes.stream().filter(w -> w.getName().equalsIgnoreCase(wineType)).findFirst();

        if (wineRegionOptional.isPresent())
        {
            return wineRegionOptional.get();
        }
        else
        {
            throw new IllegalArgumentException("Wine region not found:" + wineType);
        }
    }
}
