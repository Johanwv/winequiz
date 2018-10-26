package nl.wine.quiz.service.hibernate;

import nl.wine.quiz.model.Player;
import nl.wine.quiz.service.DbBase;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertNotNull;

public class HibernateServiceImplTest extends DbBase
{
    @Mock
    HibernateSessionFactory sessionFactory;

    @InjectMocks
    HibernateService hibernateService = new HibernateServiceImpl();

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
        Mockito.when(sessionFactory.getSession()).thenReturn(entityManager.unwrap(Session.class));
        Mockito.doNothing().when(sessionFactory).closeSession(entityManager.unwrap(Session.class));
    }

    @Test
    public void test2()
    {
        Player player = new Player();
        player.setName("Johan");

        Player player2 = new Player();
        player2.setName("Johan");

        hibernateService.saveOrUpdate(player);

        hibernateService.saveOrUpdate(player2);

        Player p = hibernateService.get(Player.class, 1);
        System.out.println(p.getPlayerId());

        assertNotNull(p);
    }


}