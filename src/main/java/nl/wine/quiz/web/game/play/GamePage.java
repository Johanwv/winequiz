package nl.wine.quiz.web.game.play;


import nl.wine.quiz.service.GameService;
import nl.wine.quiz.service.game.GameServiceImpl;
import nl.wine.quiz.util.ModelUtil;
import nl.wine.quiz.web.base.BaseWineQuizPage;

public class GamePage extends BaseWineQuizPage
{
    private GameService gameService = new GameServiceImpl();

    public GamePage()
    {
        GamePanel panel = new GamePanel("gamePanel", ModelUtil.listIModel(gameService.getQuestions()));
        panel.setOutputMarkupId(true);
        add(panel);
    }
}
