package nl.wine.quiz.service.hibernate;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Repository
public class HibernateServiceImpl implements HibernateService
{
    private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }

    private Session getSession()
    {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void saveOrUpdate(Object object)
    {
        Session session = null;
        try
        {
            session = getSession();
            session.beginTransaction();
            session.saveOrUpdate(object);

            commit(session);
            session.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
            rollBack(session);
        }
    }

    @Override
    public <T> T get(Class<T> entityType, Serializable id)
    {
        return getSession().get(entityType, id);
    }

    @Override
    public <T> List<T> getAll(Class<T> entityType)
    {
        Session session = null;
        List<T> list = new ArrayList<>();
        try
        {
            session = getSession();
            session.beginTransaction();

            Criteria criteria = session.createCriteria(entityType);
            list = (List<T>) criteria.list();

            commit(session);
            session.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
            rollBack(session);
        }
        return list;
    }

    private void commit(Session session)
    {
        session.getTransaction().commit();
    }

    private void rollBack(Session session)
    {
        session.getTransaction().rollback();
    }
}
