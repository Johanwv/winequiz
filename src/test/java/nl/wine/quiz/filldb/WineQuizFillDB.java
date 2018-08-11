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
        new WineQuizFillDB().resetAndFillDb();
        System.exit(0);
    }

    private void resetAndFillDb()
    {
        ResetTables.resetTables(hibernateService.getSession());

        List<Wine> wines = CsvReader.readFileAndCreateWines();
        hibernateService.saveOrUpdateAll(wines);
    }

}
