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

        IModel questionModel = new PropertyModel(model, "question");
        questionForm.add(new Label("question", new StringResourceModel("question", this, questionModel)));


        questionForm.add(getOptionButton("optionA", model));
        questionForm.add(getOptionButton("optionB", model));
        questionForm.add(getOptionButton("optionC", model));
        questionForm.add(getOptionButton("optionD", model));

        Label numberOfQuestionsLabel = new Label("counter", new PropertyModel<>(this, "counter"));
        numberOfQuestionsLabel.setOutputMarkupId(true);

        questionForm.add(numberOfQuestionsLabel);
        add(questionForm);
    }

    private AjaxButton getOptionButton(String id, IModel model)
    {
        IModel propertyModel = new PropertyModel<>(model, id + ".option");
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
        if (playService.isAnotherQuestion(counter++, getModelObject()))
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
