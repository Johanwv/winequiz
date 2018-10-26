package nl.wine.quiz.filldb;

import nl.wine.quiz.filldb.csv.CsvReaderAndDbFiller;
import nl.wine.quiz.filldb.game.PlayerFiller;
import nl.wine.quiz.service.hibernate.HibernateService;
import nl.wine.quiz.service.hibernate.HibernateServiceImpl;
import nl.wine.quiz.service.hibernate.HibernateSessionFactory;
import nl.wine.quiz.service.hibernate.HibernateSessionFactoryImpl;

public class WineQuizFillDB
{
    private HibernateService hibernateService = new HibernateServiceImpl();

    private HibernateSessionFactory sessionFactory = new HibernateSessionFactoryImpl();

    public static void main(String[] args)
    {
        new WineQuizFillDB().resetAndFillDb();
        System.exit(0);
    }

    private void resetAndFillDb()
    {
        ResetTables.resetTables(sessionFactory.getSession());

        CsvReaderAndDbFiller.readFileAndCreateWines(hibernateService);
        try
        {
            PlayerFiller.fillPlayer(hibernateService);
        }
        catch (Throwable throwable)
        {
            throwable.printStackTrace();
        }
    }

}
