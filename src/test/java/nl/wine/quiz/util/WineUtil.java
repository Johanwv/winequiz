package nl.wine.quiz.util;

import nl.wine.quiz.model.Wine;
import nl.wine.quiz.model.enums.WineRegion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class WineUtil
{
    static Wine createWine(String name, WineRegion region)
    {
        Wine wine = new Wine();
        wine.setWineName(name);
        wine.setWineRegion(region);
        return wine;
    }
    public static Wine createWine(String name, String region)
    {
        Wine wine = new Wine();
        wine.setWineName(name);
        wine.setWineRegion(getWineRegion(region));
        return wine;
    }

    private static WineRegion getWineRegion(String wineRegion)
    {
        List<WineRegion> wineRegions = new ArrayList<>(Arrays.asList(WineRegion.class.getEnumConstants()));

        Optional<WineRegion> wineRegionOptional = wineRegions.stream().filter(w -> w.getName().equals(wineRegion)).findFirst();

        if (wineRegionOptional.isPresent())
        {
            return wineRegionOptional.get();
        }
        else
        {
            throw new IllegalArgumentException("Wine region not found:" + wineRegion);
        }
    }
}
