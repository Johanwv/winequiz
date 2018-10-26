package nl.wine.quiz.service.hibernate;

import nl.wine.quiz.model.Player;
import nl.wine.quiz.util.PlayerUtil;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.PersistenceException;
import java.util.List;

import static org.junit.Assert.assertNotNull;

public class HibernateServiceImplTest extends HibernateServiceTestBase
{

    @Test
    public void saveOrUpdatePlayerTest() throws Throwable
    {
        Player player = PlayerUtil.getPlayer("Johan");

        hibernateService.saveOrUpdate(player);

        Player p = hibernateService.get(Player.class, player.getPlayerId());
        assertNotNull(p);
        Assert.assertEquals("Johan", p.getName());
    }

    @Test(expected = PersistenceException.class)
    public void uniqueConstraintPlayerNameTest() throws Throwable
    {
        Player player = PlayerUtil.getPlayer("Johan");

        Player player2 = PlayerUtil.getPlayer("Johan");

        hibernateService.saveOrUpdate(player);

        hibernateService.saveOrUpdate(player2);
    }

    @Test
    public void deleteTest() throws Throwable
    {
        Player player = PlayerUtil.getPlayer("Johan");

        hibernateService.saveOrUpdate(player);

        Player p = hibernateService.get(Player.class, player.getPlayerId());
        assertNotNull(p);
        Assert.assertEquals("Johan", p.getName());

        hibernateService.delete(player);

        List<Player> players = hibernateService.getAll(Player.class);
        assertNotNull(p);
        Assert.assertEquals(0, players.size());
    }

    @Test
    public void getAllPlayerTest() throws Throwable
    {
        Player player = PlayerUtil.getPlayer("Johan");
        hibernateService.saveOrUpdate(player);

        Player player2 = PlayerUtil.getPlayer("Johan1");
        hibernateService.saveOrUpdate(player2);

        List<Player> p = hibernateService.getAll(Player.class);
        assertNotNull(p);
        Assert.assertEquals(2, p.size());
    }

}