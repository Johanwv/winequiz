package nl.wine.quiz.web.game.play;


import nl.wine.quiz.web.base.BaseWineQuizPage;

public class GamePage extends BaseWineQuizPage
{
    public GamePage()
    {
        GamePanel panel = new GamePanel("gamePanel");
        panel.setOutputMarkupId(true);
        add(panel);
    }
}
