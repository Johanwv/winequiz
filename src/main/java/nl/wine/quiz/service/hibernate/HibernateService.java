package nl.wine.quiz.service.hibernate;

import java.io.Serializable;

public interface HibernateService
{
    void saveOrUpdate(Object object);

    <T> T get(Class<T> entityType, Serializable id);

}
