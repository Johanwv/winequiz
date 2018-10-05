package nl.wine.quiz.web.game.start;


import nl.wine.quiz.web.base.BaseWineQuizPage;
import nl.wine.quiz.web.game.play.RegionGamePage;
import nl.wine.quiz.web.game.play.TypeGamePage;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.StringResourceModel;

public class StartGamePage extends BaseWineQuizPage
{
    public StartGamePage()
    {
        Form<Void> form = new Form<>("gameform");


        form.add(new AjaxButton("startRegionGame", new StringResourceModel("start.region"))
        {
            @Override
            protected void onSubmit(AjaxRequestTarget target)
            {
                setResponsePage(RegionGamePage.class);
            }
        });
        form.add(new AjaxButton("startTypeGame", new StringResourceModel("start.type"))
        {
            @Override
            protected void onSubmit(AjaxRequestTarget target)
            {
                setResponsePage(TypeGamePage.class);
            }
        });
        add(form);
    }

}
