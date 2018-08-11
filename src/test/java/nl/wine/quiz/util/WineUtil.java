package nl.wine.quiz.util;

import nl.wine.quiz.model.Wine;
import nl.wine.quiz.model.enums.WineRegion;

import java.util.ArrayList;
import java.util.List;

public class WineUtil
{
    public static List<Wine> createWines()
    {
        Wine wine1 = createWine("Bourgueil", WineRegion.VALLEE_DE_LA_LOIRE);
        Wine wine2 = createWine("Pouilly-fuissé", WineRegion.BOURGOGNE);
        Wine wine3 = createWine("Sauternes", WineRegion.BORDEAUX);
        Wine wine4 = createWine("Saint-amour", WineRegion.BEAUJOLAIS);
        Wine wine5 = createWine("Riesling", WineRegion.ALSACE);
        Wine wine6 = createWine("Saint-bris", WineRegion.BOURGOGNE);
        Wine wine7 = createWine("Clairette de Die", WineRegion.VALLEE_DU_RHONE);
        Wine wine8 = createWine("Loupiac", WineRegion.BORDEAUX);

        List<Wine> wines = new ArrayList<>();
        wines.add(wine1);
        wines.add(wine2);
        wines.add(wine3);
        wines.add(wine4);
        wines.add(wine5);
        wines.add(wine6);
        wines.add(wine7);
        wines.add(wine8);

        return wines;
    }


    private static Wine createWine(String name, WineRegion region)
    {
        Wine wine = new Wine();
        wine.setWineName(name);
        wine.setWineRegion(region);
        return wine;
    }
}
