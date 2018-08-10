package nl.wine.quiz.web.game.play;


import nl.wine.quiz.service.GameService;
import nl.wine.quiz.util.ModelUtil;
import nl.wine.quiz.web.base.BaseWineQuizPage;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class GamePage extends BaseWineQuizPage
{
    @SpringBean
    private GameService gameService;

    public GamePage()
    {
        GamePanel panel = new GamePanel("gamePanel", ModelUtil.listIModel(gameService.getQuestions()));
        panel.setOutputMarkupId(true);
        add(panel);
    }
}
