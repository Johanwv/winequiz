package nl.wine.quiz.filldb.csv;

import nl.wine.quiz.model.Wine;
import nl.wine.quiz.model.enums.WineRegion;
import nl.wine.quiz.util.WineUtil;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CsvReader
{

    public static List<Wine> readFileAndCreateWines()
    {

        String path = "D:\\Documenten\\Johan\\2018\\winequiz\\src\\test\\resources\\wines.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";

        int i = 0;

        List<Wine> wines = new ArrayList<>();

        try
        {

            br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null)
            {

                // use comma as separator
                String[] wine = line.split(cvsSplitBy);

                if (i++ > 0)
                {
                    wines.add(WineUtil.createWine(wine[0], getWineRegion(wine[1])));


                }
            }

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
        return wines;
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
