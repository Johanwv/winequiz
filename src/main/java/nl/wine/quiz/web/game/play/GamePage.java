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
//        ModelUtil.listIModel(getQuestions())
        GamePanel panel = new GamePanel("gamePanel", ModelUtil.listIModel(gameService.getQuestions()));
        panel.setOutputMarkupId(true);
        add(panel);
    }

//    private IModel<List<MultipleChoiceQuestion>> getQuestions()
//    {
//        List<MultipleChoiceQuestion> questions = new ArrayList<>();
//
//        MultipleChoiceQuestion multipleChoiceQuestion = new MultipleChoiceQuestion();
//        Option optionA = new Option();
//        optionA.setOption("A");
//        optionA.setAnswer(true);
//        multipleChoiceQuestion.setOptionA(optionA);
//        multipleChoiceQuestion.setOptionB("B");
//        multipleChoiceQuestion.setOptionC("C");
//        multipleChoiceQuestion.setOptionD("D");
//        multipleChoiceQuestion.setQuestion("Wat is the answer?");
//
//        MultipleChoiceQuestion multipleChoiceQuestion2 = new MultipleChoiceQuestion();
//        Option optionD = new Option();
//        optionD.setOption("D");
//        optionD.setAnswer(true);
//        multipleChoiceQuestion2.setOptionA(optionD);
//        multipleChoiceQuestion2.setOptionB("C");
//        multipleChoiceQuestion2.setOptionC("B");
//        multipleChoiceQuestion2.setOptionD("A");
//        multipleChoiceQuestion2.setQuestion("Wat is the answer to question two?");
//
//        questions.add(multipleChoiceQuestion);
//        questions.add(multipleChoiceQuestion2);
//        return ModelUtil.listIModel(questions);
//    }
}
