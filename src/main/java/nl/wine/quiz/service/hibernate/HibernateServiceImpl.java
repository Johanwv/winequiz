package nl.wine.quiz.service.hibernate;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

@Repository
public class HibernateServiceImpl implements HibernateService
{
    @Autowired
    private HibernateSessionFactory sessionFactory;

    @Override
    public void saveOrUpdate(Object object) throws Throwable
    {
        execute(session -> session.saveOrUpdate(object));
    }


    @Override
    public <T> void saveOrUpdateAll(List<T> objects) throws Throwable
    {
        execute(session -> objects.forEach(session::saveOrUpdate));
    }

    @Override
    public void delete(Object object) throws Throwable
    {
        execute(session -> session.delete(object));
    }

    @Override
    public <T> void deleteAll(List<T> objects) throws Throwable
    {
        execute(session -> objects.forEach(session::delete));
    }

    @Override
    public <T> T get(Class<T> entityType, Serializable id)
    {
        return sessionFactory.getSession().get(entityType, id);
    }

    @Override
    public <T> List<T> getAll(Class<T> entityType)
    {
        EntityManager entityManager = sessionFactory.getEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> q = cb.createQuery(entityType);
        Root<T> c = q.from(entityType);
        q.select(c);
        TypedQuery<T> tq = entityManager.createQuery(q);

        return tq.getResultList();
    }

    private void execute(HibernateCommand action) throws Throwable
    {
        Session session = null;
        try
        {
            session = setUpTranscaction();

            action.execute(session);

            afterTransaction(session);
        }
        catch (Exception e)
        {
            doWhenExceptionThrown(session, e);
        }
    }

    private Session setUpTranscaction()
    {
        Session session = sessionFactory.getSession();
        session.beginTransaction();
        return session;
    }

    private void afterTransaction(Session session)
    {
        commit(session);
        sessionFactory.closeSession(session);
    }

    private void doWhenExceptionThrown(Session session, Exception e) throws Throwable
    {
        rollBack(session);
        throw e.getCause();
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
