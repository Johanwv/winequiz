package nl.wine.quiz.service;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.Assert.assertNotNull;

public class DbBase
{
    protected static EntityManagerFactory entityManagerFactory;
    protected static EntityManager entityManager;

    @BeforeClass
    public static void init()
    {
        entityManagerFactory = Persistence.createEntityManagerFactory("thePersistenceUnit");
        entityManager = entityManagerFactory.createEntityManager();
        assertNotNull(entityManager);
    }


    @AfterClass
    public static void tearDown()
    {
        entityManager.clear();
        entityManager.close();
        entityManagerFactory.close();
    }
}
