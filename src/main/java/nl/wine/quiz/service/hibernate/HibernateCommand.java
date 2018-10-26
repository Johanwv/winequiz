package nl.wine.quiz.service.hibernate;

import org.hibernate.Session;

public interface HibernateCommand
{
    void execute(Session session);
}
