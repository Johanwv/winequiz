package nl.wine.quiz.web.game;


import nl.wine.quiz.web.base.BaseWineQuizPage;
import nl.wine.quiz.web.game.start.StartPanel;

public class BaseGamePage extends BaseWineQuizPage
{
    public BaseGamePage()
    {
        add(new StartPanel("gameContent"));
    }
}
