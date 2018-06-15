package nl.wine.quiz.web.game.play;

import nl.wine.quiz.dto.MultipleChoiceQuestions;
import nl.wine.quiz.dto.Option;
import nl.wine.quiz.util.ModelUtil;
import nl.wine.quiz.web.game.start.StartGamePage;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GamePanel extends Panel
{
    private List<Option> options;

    private String answer;


    public GamePanel(String id)
    {
        super(id);

        Form<Void> optionsForm = new Form<>("optionsForm");

        options = getQuestions().get(0).getOptions();

        answer = determineAnswer(options);

        optionsForm.add(getOptionButton("optionA", 0));
        optionsForm.add(getOptionButton("optionB", 1));
        optionsForm.add(getOptionButton("optionC", 2));
        optionsForm.add(getOptionButton("optionD", 3));
        add(optionsForm);
    }

    private String determineAnswer(List<Option> options)
    {
        Option option = options.stream().filter(Option::isAnswer).findFirst().get();
        return option.getOption();
    }

    private List<MultipleChoiceQuestions> getQuestions()
    {
        List<Option> options = new ArrayList<>();
        Option optionA = new Option();
        optionA.setAnswer(true);
        optionA.setOption("A");

        Option optionB = new Option();
        optionB.setAnswer(false);
        optionB.setOption("B");

        Option optionC = new Option();
        optionC.setAnswer(false);
        optionC.setOption("C");

        Option optionD = new Option();
        optionD.setAnswer(false);
        optionD.setOption("D");

        options.add(optionA);
        options.add(optionB);
        options.add(optionC);
        options.add(optionD);

        Collections.shuffle(options);

        List<MultipleChoiceQuestions> questions = new ArrayList<>();

        MultipleChoiceQuestions multipleChoiceQuestions = new MultipleChoiceQuestions();
        multipleChoiceQuestions.setOptions(options);
        multipleChoiceQuestions.setQuestion("Wat is the answer?");

        questions.add(multipleChoiceQuestions);
        return questions;
    }

    private AjaxButton getOptionButton(String id, int optionNumber)
    {
        IModel model = new PropertyModel<>(ModelUtil.createModel(options.get(optionNumber)), "option");
        AjaxButton ajaxButton = new AjaxButton(id, model)
        {
            @Override
            protected void onSubmit(AjaxRequestTarget target)
            {
                setResponsePage(StartGamePage.class);
            }
        };
        return ajaxButton;
    }
}
