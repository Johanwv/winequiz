package nl.wine.quiz.filldb;

import nl.wine.quiz.filldb.csv.CsvReaderAndDbFiller;
import nl.wine.quiz.service.hibernate.HibernateService;
import nl.wine.quiz.service.hibernate.HibernateServiceImpl;

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

        CsvReaderAndDbFiller.readFileAndCreateWines(hibernateService);
    }

}
