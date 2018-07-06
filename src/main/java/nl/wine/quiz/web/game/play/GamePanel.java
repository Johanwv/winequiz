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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GamePanel extends GenericPanel<List<MultipleChoiceQuestion>>
{
    private int counter;

    private int score;

    private Form<MultipleChoiceQuestion> questionForm;

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
        score += isCorrect(choice);
        if (isAnotherQuestion())
        {
            displayNextQuestion(target);
        }
        else
        {
            goToStartPage();
        }
    }

    private int isCorrect(String choice)
    {
        Optional<String> answer = determineAnswer();
        if (answer.isPresent() && answer.get().equals(choice))
        {
            return 1;
        }
        return 0;
    }

    private Optional<String> determineAnswer()
    {
        MultipleChoiceQuestion multipleChoiceQuestion = questionForm.getModelObject();
        List<Option> options = makeListOfOptions(multipleChoiceQuestion);
        return options.stream().filter(Option::isAnswer).map(Option::getOption).findFirst();
    }

    private List<Option> makeListOfOptions(MultipleChoiceQuestion multipleChoiceQuestion)
    {
        List<Option> options = new ArrayList<>();
        options.add(multipleChoiceQuestion.getOptionA());
        options.add(multipleChoiceQuestion.getOptionB());
        options.add(multipleChoiceQuestion.getOptionC());
        options.add(multipleChoiceQuestion.getOptionD());
        return options;
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
