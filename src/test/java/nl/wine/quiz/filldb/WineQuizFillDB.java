package nl.wine.quiz.filldb;

import nl.wine.quiz.filldb.csv.CsvReaderAndDbFiller;
import nl.wine.quiz.filldb.game.PlayerFiller;
import nl.wine.quiz.service.hibernate.HibernateService;
import nl.wine.quiz.service.hibernate.HibernateSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml", "/applicationContext.xml"})
public class WineQuizFillDB
{
    @Autowired
    private HibernateService hibernateService;

    @Autowired
    private HibernateSessionFactory sessionFactory;

    @Test
    public void filldb()
    {
        resetAndFillDb();
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
