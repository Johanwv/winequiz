package nl.wine.quiz.web.game.play;

import nl.wine.quiz.dto.MultipleChoiceQuestion;
import nl.wine.quiz.dto.Option;
import nl.wine.quiz.util.ModelUtil;
import nl.wine.quiz.web.game.start.StartGamePage;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import java.util.List;

public class GamePanel extends GenericPanel<List<MultipleChoiceQuestion>>
{

    private int counter;

    private Form<Void> questionForm;

    public GamePanel(String id, IModel<List<MultipleChoiceQuestion>> multipleChoiceQuestionsModel)
    {
        super(id, multipleChoiceQuestionsModel);

        IModel model = ModelUtil.createModel(getModelObject().get(counter));

        questionForm = new Form<>("optionsForm", model);
        questionForm.setOutputMarkupId(true);

        questionForm.add(getOptionButton("optionA", model));
        questionForm.add(getOptionButton("optionB", model));
        questionForm.add(getOptionButton("optionC", model));
        questionForm.add(getOptionButton("optionD", model));

        Label numberOfQuestionsLabel = new Label("counter", new PropertyModel<>(this, "counter"));
        numberOfQuestionsLabel.setOutputMarkupId(true);

        questionForm.add(numberOfQuestionsLabel);
        add(questionForm);
    }

    private String determineAnswer(List<Option> options)
    {
        Option option = options.stream().filter(Option::isAnswer).findFirst().get();
        return option.getOption();
    }

    private AjaxButton getOptionButton(String id, IModel model)
    {
        IModel propertyModel = new PropertyModel<>(model, id);
        return new AjaxButton(id, propertyModel)
        {
            @Override
            protected void onSubmit(AjaxRequestTarget target)
            {
                determineNextStep(target);
            }
        };
    }

    private void determineNextStep(AjaxRequestTarget target)
    {
        if (isAnotherQuestion())
        {
            displayNextQuestion(target);
        } else
        {
            goToStartPage();
        }
    }

    private boolean isAnotherQuestion()
    {
        return ++counter < getModelObject().size();
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
