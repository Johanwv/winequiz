package nl.wine.quiz.service.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.util.Properties;

@Service
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
            Configuration configuration = new Configuration();
            Properties p = new Properties();
            try
            {
                p.load(getClass().getClassLoader().getResourceAsStream("hibernate-config.properties"));
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            configuration.setProperties(p);
            return configuration.configure().buildSessionFactory();
        }
        return sessionFactory;
    }

    @Override
    public EntityManager getEntityManager()
    {
        return sessionFactory.createEntityManager();
    }
}
