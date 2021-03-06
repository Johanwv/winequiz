package nl.wine.quiz.web.game.play;

import nl.wine.quiz.dto.MultipleChoiceQuestion;
import nl.wine.quiz.model.enums.GameChoice;
import nl.wine.quiz.service.PlayService;
import nl.wine.quiz.util.ModelUtil;
import nl.wine.quiz.web.game.start.StartGamePage;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GamePanel extends GenericPanel<List<MultipleChoiceQuestion>>
{
    @SpringBean
    private PlayService playService;

    private int counter;

    private int score;

    private Form<MultipleChoiceQuestion> questionForm;

    private CopyOnWriteArrayList<MultipleChoiceQuestion> multipleChoiceQuestions;

    private GameChoice gameChoice;

    public GamePanel(String id, IModel<List<MultipleChoiceQuestion>> multipleChoiceQuestionsModel, GameChoice gameChoice)
    {
        super(id, multipleChoiceQuestionsModel);
        this.gameChoice = gameChoice;
        this.multipleChoiceQuestions = new CopyOnWriteArrayList<>(multipleChoiceQuestionsModel.getObject());

        IModel<MultipleChoiceQuestion> model = ModelUtil.createModel(multipleChoiceQuestions.get(0));

        questionForm = new Form<>("optionsForm", model);
        questionForm.setOutputMarkupId(true);

        createNumberOfQuestions();

        Label totalNumbQuestions = new Label("totalNumbQuestions", "/" + getModelObject().size());
        questionForm.add(totalNumbQuestions);

        createScore();

        createQuestion(model);

        createButtons(model);

        add(questionForm);
    }

    private void createNumberOfQuestions()
    {
        IModel numberOfQuestionsModel = new PropertyModel<>(this, "counter");
        Label numbQuestion = new Label("numbQuestionLeft", new StringResourceModel("game.number.of.questions", this, numberOfQuestionsModel));
        numbQuestion.setOutputMarkupId(true);
        questionForm.add(numbQuestion);
    }

    private void createScore()
    {
        IModel scoreModel = new PropertyModel<>(this, "score");
        Label score = new Label("score", new StringResourceModel("game.score", this, scoreModel));
        score.setOutputMarkupId(true);
        questionForm.add(score);
    }

    private void createQuestion(IModel model)
    {
        IModel questionModel = new PropertyModel(model, "question");
        String resourceKey = getResourceKeyQuestion();
        questionForm.add(new Label("question", new StringResourceModel(resourceKey, this, questionModel)));
    }

    private String getResourceKeyQuestion()
    {
        if (gameChoice.equals(GameChoice.REGION))
        {
            return "wine.region.question";
        }
        else if (gameChoice.equals(GameChoice.TYPE))
        {
            return "wine.type.question";
        }
        throw new IllegalStateException("No valid game choice: " + gameChoice);
    }

    private void createButtons(IModel model)
    {
        questionForm.add(getOptionButton("optionA", model));
        questionForm.add(getOptionButton("optionB", model));
        questionForm.add(getOptionButton("optionC", model));
        questionForm.add(getOptionButton("optionD", model));
    }

    private AjaxButton getOptionButton(String id, IModel model)
    {
        IModel propertyModel = new PropertyModel<>(model, id + ".choice");
        return new AjaxButton(id, propertyModel)
        {
            @Override
            protected void onSubmit(AjaxRequestTarget target)
            {
                determineNextStep(target, getModelObject());
            }
        };
    }

    private void determineNextStep(AjaxRequestTarget target, String choice)
    {
        score += playService.isCorrect(choice, questionForm.getModelObject());
        if (playService.isAnotherQuestion(++counter, getModelObject()))
        {
            displayNextQuestion(target);
        }
        else
        {
            goToStartPage();
        }
    }

    private void displayNextQuestion(AjaxRequestTarget target)
    {
        multipleChoiceQuestions.remove(0);
        questionForm.setDefaultModelObject(multipleChoiceQuestions.get(0));
        target.add(questionForm);
    }

    private void goToStartPage()
    {
        setResponsePage(StartGamePage.class);
    }
}
