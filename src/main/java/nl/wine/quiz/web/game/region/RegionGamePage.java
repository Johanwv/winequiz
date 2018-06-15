package nl.wine.quiz.web.game.region;


import nl.wine.quiz.dto.MultipleChoice;
import nl.wine.quiz.dto.Option;
import nl.wine.quiz.util.ModelUtil;
import nl.wine.quiz.web.base.BaseWineQuizPage;
import nl.wine.quiz.web.game.StartGamePage;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RegionGamePage extends BaseWineQuizPage
{
    private List<Option> options;

    private String answer;

    public RegionGamePage()
    {
        Form<Void> optionsForm = new Form<>("optionsForm");

        options = getQuestions().get(0).getOptions();

        answer = determineAnswer(options);

        optionsForm.add(getStopButton("optionA", 0));
        optionsForm.add(getStopButton("optionB", 1));
        optionsForm.add(getStopButton("optionC", 2));
        optionsForm.add(getStopButton("optionD", 3));
        add(optionsForm);
    }

    private String determineAnswer(List<Option> options)
    {
        Option option = options.stream().filter(Option::isAnswer).findFirst().get();
        return option.getOption();
    }

    private List<MultipleChoice> getQuestions()
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

        List<MultipleChoice> questions = new ArrayList<>();

        MultipleChoice multipleChoice = new MultipleChoice();
        multipleChoice.setOptions(options);
        multipleChoice.setQuestion("Wat is the answer?");

        questions.add(multipleChoice);
        return questions;
    }

    private AjaxButton getStopButton(String id, int optionNumber)
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
