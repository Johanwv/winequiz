package nl.wine.quiz.filldb.csv;

import nl.wine.quiz.model.Variety;
import nl.wine.quiz.model.Wine;
import nl.wine.quiz.service.hibernate.HibernateService;
import nl.wine.quiz.util.VarietyUtil;
import nl.wine.quiz.util.WineUtil;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CsvReaderAndDbFiller
{

    public static void readFileAndCreateWines(HibernateService hibernateService)
    {

        String path = "D:\\Documenten\\Johan\\2018\\winequiz\\src\\test\\resources\\wines.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";

        int i = 0;

        List<Wine> wines = new ArrayList<>();
        List<Variety> varieties = new ArrayList<>();

        try
        {

            br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null)
            {
                // use comma as separator
                String[] wine = line.split(cvsSplitBy);

                if (i++ > 0)
                {
                    String name = wine[0];
                    String region = wine[1];
                    Wine newWine = WineUtil.createWine(name, region);

                    String type = wine[3];
                    Variety newVariety = VarietyUtil.createVariety(type);

                    coupleWineAndVariety(newWine, newVariety);

                    wines.add(newWine);
                    varieties.add(newVariety);
                }
            }
            hibernateService.saveOrUpdateAll(wines);
            hibernateService.saveOrUpdateAll(varieties);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (br != null)
            {
                try
                {
                    br.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void coupleWineAndVariety(Wine newWine, Variety newVariety)
    {
        coupleVarietyToWine(newWine, newVariety);

        coupleWineToVariety(newWine, newVariety);
    }

    private static void coupleWineToVariety(Wine newWine, Variety newVariety)
    {
        if (newVariety.getWines() != null)
        {
            newVariety.getWines().add(newWine);
        }
        else
        {
            Set<Wine> wine = new HashSet<>();
            wine.add(newWine);
            newVariety.setWines(wine);
        }
    }

    private static void coupleVarietyToWine(Wine newWine, Variety newVariety)
    {
        if (newWine.getVarieties() != null)
        {
            newWine.getVarieties().add(newVariety);
        }
        else
        {
            Set<Variety> varietie = new HashSet<>();
            varietie.add(newVariety);
            newWine.setVarieties(new HashSet<>(varietie));
        }
    }

}
