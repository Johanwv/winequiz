package nl.wine.quiz.service.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.List;

public class HibernateServiceImpl implements HibernateService
{
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
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
        return getSession().createCriteria(entityType).list();
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
