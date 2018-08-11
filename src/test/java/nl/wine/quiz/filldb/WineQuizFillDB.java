package nl.wine.quiz.filldb;

import nl.wine.quiz.filldb.csv.CsvReader;
import nl.wine.quiz.model.Wine;
import nl.wine.quiz.service.hibernate.HibernateService;
import nl.wine.quiz.service.hibernate.HibernateServiceImpl;

import java.util.List;

public class WineQuizFillDB
{
    private HibernateService hibernateService = new HibernateServiceImpl();

    public static void main(String[] args)
    {
        new WineQuizFillDB().createAndFillDb();
    }

    private void createAndFillDb()
    {
        saveWines();
    }

    private void saveWines()
    {
        ResetTables.resetTables(hibernateService.getSession());


        List<Wine> wines = CsvReader.readFileAndCreateWines();
        hibernateService.saveOrUpdateAll(wines);
    }


}
