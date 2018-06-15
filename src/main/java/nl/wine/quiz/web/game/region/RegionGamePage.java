package nl.wine.quiz.web.game.region;


import nl.wine.quiz.web.base.BaseWineQuizPage;
import nl.wine.quiz.web.game.StartGamePage;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;

public class RegionGamePage extends BaseWineQuizPage
{
    public RegionGamePage()
    {
        Form<Void> optionsForm = new Form<>("optionsForm");
        optionsForm.add(getStopButton("optionA"));
        optionsForm.add(getStopButton("optionB"));
        optionsForm.add(getStopButton("optionC"));
        optionsForm.add(getStopButton("optionD"));
        add(optionsForm);
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
