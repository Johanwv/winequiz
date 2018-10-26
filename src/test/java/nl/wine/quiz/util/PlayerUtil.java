package nl.wine.quiz.util;

import nl.wine.quiz.model.Player;

public class PlayerUtil
{
    public static Player getPlayer(String name)
    {
        Player player = new Player();
        player.setName(name);
        return player;
    }
}
