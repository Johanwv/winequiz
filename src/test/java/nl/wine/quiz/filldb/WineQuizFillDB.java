package nl.wine.quiz.filldb;

import nl.wine.quiz.model.Wine;
import nl.wine.quiz.model.enums.WineRegion;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class WineQuizFillDB
{
    private SessionFactory sessionFactory;

    private Session session;

    public static void main(String[] args)
    {
        new WineQuizFillDB().createAndFillDb();
    }

    private void createAndFillDb()
    {
        sessionFactory = createSessionFactory();
        session = createSession();
        saveWines();
    }

    private SessionFactory createSessionFactory()
    {
        return new Configuration().configure().buildSessionFactory();
    }

    private Session createSession()
    {
        return sessionFactory.getCurrentSession();
    }

    private void saveWines()
    {
        try
        {
            session.beginTransaction();

            Wine wine1 = createWine("Bourgueil", WineRegion.VALLEE_DE_LA_LOIRE);
            Wine wine2 = createWine("Pouilly-fuissé", WineRegion.BOURGOGNE);
            Wine wine3 = createWine("Sauternes", WineRegion.BORDEAUX);
            Wine wine4 = createWine("Saint-amour", WineRegion.BEAUJOLAIS);

            session.saveOrUpdate(wine1);
            session.saveOrUpdate(wine2);
            session.saveOrUpdate(wine3);
            session.saveOrUpdate(wine4);

            commit();
            session.close();
        } catch (Exception e)
        {
            System.out.println(e);
            rollBack();
        }
    }

    private Wine createWine(String name, WineRegion region)
    {
        Wine wine = new Wine();
        wine.setWineName(name);
        wine.setWineRegion(region);
        return wine;
    }

    private void commit()
    {
        session.getTransaction().commit();
    }

    private void rollBack()
    {
        session.getTransaction().rollback();
    }
}
