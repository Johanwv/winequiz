package nl.wine.quiz.web.game.play;

import nl.wine.quiz.dto.MultipleChoiceQuestion;
import nl.wine.quiz.service.PlayService;
import nl.wine.quiz.service.game.PlayServiceImpl;
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

import java.util.List;

public class GamePanel extends GenericPanel<List<MultipleChoiceQuestion>>
{
    private PlayService playService = new PlayServiceImpl();

    private int counter;

    private int score;

    private Form<MultipleChoiceQuestion> questionForm;

    public GamePanel(String id, IModel<List<MultipleChoiceQuestion>> multipleChoiceQuestionsModel)
    {
        super(id, multipleChoiceQuestionsModel);

        IModel model = ModelUtil.createModel(getModelObject().get(counter));

        questionForm = new Form<>("optionsForm", model);
        questionForm.setOutputMarkupId(true);

        createScore();

        createQuestion(model);

        createButtons(model);

        add(questionForm);
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
        questionForm.add(new Label("question", new StringResourceModel("wine.question", this, questionModel)));
    }

    private void createButtons(IModel model)
    {
        questionForm.add(getOption("optionA", model));
        questionForm.add(getOption("optionB", model));
        questionForm.add(getOption("optionC", model));
        questionForm.add(getOption("optionD", model));
    }

    private AjaxButton getOption(String id, IModel model)
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
        questionForm.setDefaultModelObject(getModelObject().get(counter));
        target.add(questionForm);
    }

    private void goToStartPage()
    {
        setResponsePage(StartGamePage.class);
    }
}
