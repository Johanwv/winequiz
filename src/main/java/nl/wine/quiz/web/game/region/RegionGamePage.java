package nl.wine.quiz.web.game.region;


import nl.wine.quiz.dto.MultipleChoice;
import nl.wine.quiz.dto.Option;
import nl.wine.quiz.web.base.BaseWineQuizPage;
import nl.wine.quiz.web.game.StartGamePage;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;

import java.util.ArrayList;
import java.util.List;

public class RegionGamePage extends BaseWineQuizPage
{
    private List<MultipleChoice> options;

    public RegionGamePage()
    {
        options = getOptions();

        Form<Void> optionsForm = new Form<>("optionsForm");
        optionsForm.add(getStopButton("optionA"));
        optionsForm.add(getStopButton("optionB"));
        optionsForm.add(getStopButton("optionC"));
        optionsForm.add(getStopButton("optionD"));
        add(optionsForm);
    }

    private List<MultipleChoice> getOptions()
    {
        List<Option> options = new ArrayList<>();
        Option optionA = new Option();
        optionA.setAnswer(true);
        optionA.setOption("A");

        Option optionB = new Option();
        optionA.setAnswer(false);
        optionA.setOption("B");

        Option optionC = new Option();
        optionA.setAnswer(false);
        optionA.setOption("C");

        Option optionD = new Option();
        optionA.setAnswer(false);
        optionA.setOption("D");

        options.add(optionA);
        options.add(optionB);
        options.add(optionC);
        options.add(optionD);

        List<MultipleChoice> multipleChoices = new ArrayList<>();

        MultipleChoice multipleChoice = new MultipleChoice();
        multipleChoice.setOptions(options);
        multipleChoice.setQuestion("Wat is the answer?");

        return multipleChoices;
    }

    private AjaxButton getStopButton(String id)
    {
        AjaxButton ajaxButton = new AjaxButton(id)
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
