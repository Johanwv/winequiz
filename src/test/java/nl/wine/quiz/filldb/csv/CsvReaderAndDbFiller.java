package nl.wine.quiz.filldb.csv;

import nl.wine.quiz.model.Variety;
import nl.wine.quiz.model.Wine;
import nl.wine.quiz.service.hibernate.HibernateService;
import nl.wine.quiz.util.VarietyUtil;
import nl.wine.quiz.util.WineUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
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
        Set<Variety> varieties = new HashSet<>();

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

                    Set<Variety> varietiesNewWine = createVarieties(wine);

                    varietiesNewWine.forEach(varietyWine -> coupleWineAndVariety(newWine, determineVariety(varietyWine, varieties)));

                    wines.add(newWine);
                }
            }
            hibernateService.saveOrUpdateAll(wines);
            hibernateService.saveOrUpdateAll(new ArrayList<>(varieties));
        }
        catch (Throwable e)
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

    private static Set<Variety> createVarieties(String[] wine)
    {
        Set<Variety> varietiesNewWine = new HashSet<>();

        String type1 = wine[3];
        Variety newVariety1 = VarietyUtil.createVarietie(type1);
        varietiesNewWine.add(newVariety1);

        if (wine.length > 4)
        {
            String type2 = wine[5];
            Variety newVariety2 = VarietyUtil.createVarietie(type2);
            varietiesNewWine.add(newVariety2);
        }
        if (wine.length > 6)
        {
            String type3 = wine[7];
            Variety newVariety3 = VarietyUtil.createVarietie(type3);
            varietiesNewWine.add(newVariety3);

        }
        if (wine.length > 8)
        {
            String type4 = wine[9];
            Variety newVariety4 = VarietyUtil.createVarietie(type4);
            varietiesNewWine.add(newVariety4);
        }

        return varietiesNewWine;
    }

    private static Variety determineVariety(Variety varietyWine, Set<Variety> varieties)
    {
        Optional<Variety> optionalVariety = varieties.stream().filter(v -> v.getWineType().equals(varietyWine.getWineType())).findFirst();
        if (optionalVariety.isPresent())
        {
            return optionalVariety.get();
        }
        else
        {
            varieties.add(varietyWine);
            return varietyWine;
        }

    }

    public static void coupleWineAndVariety(Wine newWine, Variety newVariety)
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
