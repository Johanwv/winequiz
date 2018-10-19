package nl.wine.quiz.filldb.game;

import nl.wine.quiz.model.Player;
import nl.wine.quiz.service.hibernate.HibernateService;

public class PlayerFiller
{

    public static void fillPlayer(HibernateService hibernateService)
    {
        Player player1 = createPlayer("Johan");
        hibernateService.saveOrUpdate(player1);

        Player player2 = createPlayer("Femke");
        hibernateService.saveOrUpdate(player2);
    }

    public static Player createPlayer(String name)
    {
        Player player = new Player();
        player.setName(name);
        return player;
    }
}
