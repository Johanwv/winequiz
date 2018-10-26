package nl.wine.quiz.service;

import nl.wine.quiz.service.hibernate.HibernateService;
import nl.wine.quiz.service.hibernate.HibernateServiceImpl;
import nl.wine.quiz.service.hibernate.HibernateSessionFactory;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.Assert.assertNotNull;

public class HibernateServiceTestBase
{
    protected static EntityManagerFactory entityManagerFactory;

    protected static EntityManager entityManager;

    @Mock
    protected HibernateSessionFactory sessionFactory;

    @InjectMocks
    protected HibernateService hibernateService = new HibernateServiceImpl();

    @Before
    public void setUp()
    {
        entityManagerFactory = Persistence.createEntityManagerFactory("thePersistenceUnit");
        entityManager = entityManagerFactory.createEntityManager();
        assertNotNull(entityManager);

        MockitoAnnotations.initMocks(this);
        Mockito.when(sessionFactory.getSession()).thenReturn(entityManager.unwrap(Session.class));
        Mockito.when(sessionFactory.getEntityManager()).thenReturn(entityManager);
        Mockito.doNothing().when(sessionFactory).closeSession(entityManager.unwrap(Session.class));
    }

    @After
    public void rollBack()
    {
        entityManager.clear();
        entityManager.close();
        entityManagerFactory.close();
    }

}
