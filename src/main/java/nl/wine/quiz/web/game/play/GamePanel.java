package nl.wine.quiz.web.game.play;

import nl.wine.quiz.dto.MultipleChoiceQuestion;
import nl.wine.quiz.dto.Option;
import nl.wine.quiz.util.ModelUtil;
import nl.wine.quiz.web.game.start.StartGamePage;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import java.util.List;

public class GamePanel extends Panel
{
    private List<MultipleChoiceQuestion> questions;

    private List<Option> options;

    private String answer;

    private int counter;

    private Form<List<Option>> questionForm;

    public GamePanel(String id, IModel<List<MultipleChoiceQuestion>> multipleChoiceQuestionsModel)
    {
        super(id);

        questions = multipleChoiceQuestionsModel.getObject();

        options = multipleChoiceQuestionsModel.getObject().get(counter).getOptions();

        answer = determineAnswer(options);

        questionForm = new Form<>("optionsForm", ModelUtil.listIModel(options));
        questionForm.setOutputMarkupId(true);

        RadioGroup<List<Option>> group = new RadioGroup<>("multipleChoiceQuestion", ModelUtil.listIModel(options));
        group.add(getOptionButton("optionA", 0));
        group.add(getOptionButton("optionB", 1));
        group.add(getOptionButton("optionC", 2));
        group.add(getOptionButton("optionD", 3));

        questionForm.add(group);

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

    private AjaxButton getOptionButton(String id, int optionNumber)
    {
        IModel model = new PropertyModel<>(ModelUtil.createModel(options.get(optionNumber)), "option");
        AjaxButton ajaxButton = new AjaxButton(id, model, questionForm)
        {
            @Override
            protected void onSubmit(AjaxRequestTarget target)
            {
                if (++counter < questions.size())
                {

                    List<Option> newOptions = questions.get(counter).getOptions();
                    for (int i = 0; i < 4; i++)
                    {
                        options.get(i).setAnswer(newOptions.get(i).isAnswer());
                        options.get(i).setOption(newOptions.get(i).getOption());
                    }
                    answer = determineAnswer(newOptions);

                    target.add(questionForm);
                } else
                {
                    setResponsePage(StartGamePage.class);
                }
            }
        };
        return ajaxButton;
    }
}
