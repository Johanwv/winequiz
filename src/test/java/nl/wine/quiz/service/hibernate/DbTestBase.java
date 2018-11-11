package nl.wine.quiz.service.hibernate;

import org.hibernate.Session;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.Assert.assertNotNull;

public abstract class DbTestBase
{
    protected static EntityManagerFactory entityManagerFactory;

    protected static EntityManager entityManager;

    @Mock
    protected HibernateSessionFactory sessionFactory;

    @InjectMocks
    protected HibernateService hibernateService = new HibernateServiceImpl();

    @BeforeClass
    public static void init()
    {

        entityManagerFactory = Persistence.createEntityManagerFactory("thePersistenceUnit");
        entityManager = entityManagerFactory.createEntityManager();
        assertNotNull(entityManager);
    }

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
        Mockito.when(sessionFactory.getSession()).thenReturn(entityManager.unwrap(Session.class));
        Mockito.when(sessionFactory.getEntityManager()).thenReturn(entityManager);
        Mockito.doNothing().when(sessionFactory).closeSession(entityManager.unwrap(Session.class));
    }

    @AfterClass
    public static void tearDown()
    {
        entityManager.clear();
        entityManager.close();
        entityManagerFactory.close();
    }

    @After
    public abstract void rollBack() throws Throwable;

}
