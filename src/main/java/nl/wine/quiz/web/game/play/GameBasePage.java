package nl.wine.quiz.web.game.play;


import nl.wine.quiz.model.enums.GameChoice;
import nl.wine.quiz.service.GameService;
import nl.wine.quiz.util.ModelUtil;
import nl.wine.quiz.web.base.BaseWineQuizPage;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class GameBasePage extends BaseWineQuizPage
{
    @SpringBean
    private GameService gameService;

    public GameBasePage(GameChoice gameChoice)
    {
        GamePanel panel = new GamePanel("gamePanel", ModelUtil.listIModel(gameService.getQuestions(gameChoice)), gameChoice);

        add(panel.setOutputMarkupId(true));
    }
}
