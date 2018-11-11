package nl.wine.quiz.service.hibernate;

import org.hibernate.Session;

import javax.persistence.EntityManager;


public interface HibernateSessionFactory
{
    Session getSession();

    void closeSession(Session session);

    EntityManager getEntityManager();
}
