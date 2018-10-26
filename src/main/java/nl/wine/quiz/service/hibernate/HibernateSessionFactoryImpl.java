package nl.wine.quiz.service.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;

public class HibernateSessionFactoryImpl implements HibernateSessionFactory
{
    private SessionFactory sessionFactory = getSessionFactory();

    @Override
    public Session getSession()
    {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void closeSession(Session session)
    {
        session.close();
    }

    public SessionFactory getSessionFactory()
    {
        if (sessionFactory == null)
        {
            return new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }

    @Override
    public EntityManager getEntityManager()
    {
        return sessionFactory.createEntityManager();
    }
}
