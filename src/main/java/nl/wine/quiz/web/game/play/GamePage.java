package nl.wine.quiz.web.game.play;


import nl.wine.quiz.dto.MultipleChoiceQuestion;
import nl.wine.quiz.service.GameService;
import nl.wine.quiz.service.game.GameServiceImpl;
import nl.wine.quiz.util.ModelUtil;
import nl.wine.quiz.web.base.BaseWineQuizPage;
import org.apache.wicket.model.IModel;

import java.util.ArrayList;
import java.util.List;

public class GamePage extends BaseWineQuizPage
{
    private GameService gameService = new GameServiceImpl();

    public GamePage()
    {
        GamePanel panel = new GamePanel("gamePanel", ModelUtil.listIModel(gameService.getQuestions()));
        panel.setOutputMarkupId(true);
        add(panel);
    }

    private IModel<List<MultipleChoiceQuestion>> getQuestions()
    {
        List<MultipleChoiceQuestion> questions = new ArrayList<>();

        MultipleChoiceQuestion multipleChoiceQuestion = new MultipleChoiceQuestion();
        multipleChoiceQuestion.setOptionA("A");
        multipleChoiceQuestion.setOptionB("B");
        multipleChoiceQuestion.setOptionC("C");
        multipleChoiceQuestion.setOptionD("D");
        multipleChoiceQuestion.setQuestion("Wat is the answer?");

        MultipleChoiceQuestion multipleChoiceQuestion2 = new MultipleChoiceQuestion();
        multipleChoiceQuestion2.setOptionA("D");
        multipleChoiceQuestion2.setOptionB("C");
        multipleChoiceQuestion2.setOptionC("B");
        multipleChoiceQuestion2.setOptionD("A");
        multipleChoiceQuestion2.setQuestion("Wat is the answer to question two?");

        questions.add(multipleChoiceQuestion);
        questions.add(multipleChoiceQuestion2);
        return ModelUtil.listIModel(questions);
    }
}
