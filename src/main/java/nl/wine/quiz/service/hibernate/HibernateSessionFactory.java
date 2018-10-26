package nl.wine.quiz.service.hibernate;

import org.hibernate.Session;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
public interface HibernateSessionFactory
{
    Session getSession();

    void closeSession(Session session);

    EntityManager getEntityManager();
}
