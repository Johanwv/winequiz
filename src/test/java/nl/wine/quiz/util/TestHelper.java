package nl.wine.quiz.util;

import nl.wine.quiz.filldb.csv.CsvReaderAndDbFiller;
import nl.wine.quiz.model.Variety;
import nl.wine.quiz.model.Wine;
import nl.wine.quiz.model.enums.WineRegion;
import nl.wine.quiz.model.enums.WineType;

import java.util.ArrayList;
import java.util.List;

public class TestHelper
{
    public static List<Wine> createWinesWithVarities()
    {
        Variety red = VarietyUtil.createVarietie(WineType.RED);
        Variety white = VarietyUtil.createVarietie(WineType.WHITE);
        Variety sweet = VarietyUtil.createVarietie(WineType.SWEET);
        Variety rose = VarietyUtil.createVarietie(WineType.ROSE);
        Variety sparkling = VarietyUtil.createVarietie(WineType.SPARKLING);


        Wine wine1 = WineUtil.createWine("Bourgueil", WineRegion.VALLEE_DE_LA_LOIRE);
        CsvReaderAndDbFiller.coupleWineAndVariety(wine1, red);
        CsvReaderAndDbFiller.coupleWineAndVariety(wine1, rose);
        CsvReaderAndDbFiller.coupleWineAndVariety(wine1, white);

        Wine wine2 = WineUtil.createWine("Pouilly-fuissé", WineRegion.BOURGOGNE);
        CsvReaderAndDbFiller.coupleWineAndVariety(wine2, white);

        Wine wine3 = WineUtil.createWine("Sauternes", WineRegion.BORDEAUX);
        CsvReaderAndDbFiller.coupleWineAndVariety(wine3, sweet);

        Wine wine4 = WineUtil.createWine("Saint-amour", WineRegion.BEAUJOLAIS);
        CsvReaderAndDbFiller.coupleWineAndVariety(wine4, red);

        Wine wine5 = WineUtil.createWine("Riesling", WineRegion.ALSACE);
        CsvReaderAndDbFiller.coupleWineAndVariety(wine5, white);

        Wine wine6 = WineUtil.createWine("Saint-bris", WineRegion.BOURGOGNE);
        CsvReaderAndDbFiller.coupleWineAndVariety(wine6, white);

        Wine wine7 = WineUtil.createWine("Clairette de Die", WineRegion.VALLEE_DU_RHONE);
        CsvReaderAndDbFiller.coupleWineAndVariety(wine7, sparkling);

        Wine wine8 = WineUtil.createWine("Loupiac", WineRegion.BORDEAUX);
        CsvReaderAndDbFiller.coupleWineAndVariety(wine8, sparkling);

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
}
