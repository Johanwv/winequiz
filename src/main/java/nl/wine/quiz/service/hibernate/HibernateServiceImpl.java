package nl.wine.quiz.service.hibernate;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Repository
public class HibernateServiceImpl implements HibernateService
{
    @Autowired
    private HibernateSessionFactory sessionFactory;

    @Override
    public void saveOrUpdate(Object object)
    {
        Session session = null;
        try
        {
            session = sessionFactory.getSession();
            session.beginTransaction();
            session.saveOrUpdate(object);

            commit(session);
            sessionFactory.closeSession(session);
        }
        catch (Exception e)
        {
            System.out.println(e);
            rollBack(session);
        }
    }

    @Override
    public <T> void saveOrUpdateAll(List<T> objects)
    {
        Session session = null;
        try
        {
            session = sessionFactory.getSession();
            session.beginTransaction();

            for (Object object : objects)
            {
                session.saveOrUpdate(object);
            }

            commit(session);
            sessionFactory.closeSession(session);
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
        return sessionFactory.getSession().get(entityType, id);
    }

    @Override
    public <T> List<T> getAll(Class<T> entityType)
    {
        Session session = null;
        List<T> list = new ArrayList<>();
        try
        {
            session = sessionFactory.getSession();
            session.beginTransaction();

            String tableName = entityType.getTypeName().substring(entityType.getTypeName().lastIndexOf('.') + 1).trim();
            list = session.createQuery("FROM " + tableName).list();

            commit(session);
            sessionFactory.closeSession(session);
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
