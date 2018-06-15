package nl.wine.quiz.web.game.start;


import nl.wine.quiz.web.base.BaseWineQuizPage;
import nl.wine.quiz.web.game.play.GamePage;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;

public class StartGamePage extends BaseWineQuizPage
{
    public StartGamePage()
    {
        Form<Void> form = new Form<>("gameform");
        form.add(new AjaxButton("startGame")
        {
            @Override
            protected void onSubmit(AjaxRequestTarget target)
            {
                setResponsePage(GamePage.class);
            }
        });
        add(form);
    }

}
