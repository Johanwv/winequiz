package nl.wine.quiz.service.player;

import nl.wine.quiz.model.Player;
import nl.wine.quiz.service.hibernate.HibernateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl implements PlayerService
{
    @Autowired
    private HibernateService hibernateService;

    @Override
    public void savePlayer(String name) throws Throwable
    {
        Player player = new Player();
        player.setName(name);

        hibernateService.saveOrUpdate(player);
    }
}
