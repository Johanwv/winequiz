package nl.wine.quiz.service.hibernate;

import java.io.Serializable;
import java.util.List;

public interface HibernateService extends Serializable
{
    void saveOrUpdate(Object object);

    <T> void saveOrUpdateAll(List<T> objects);

    <T> T get(Class<T> entityType, Serializable id);

    <T> List<T> getAll(Class<T> entityType);

}
