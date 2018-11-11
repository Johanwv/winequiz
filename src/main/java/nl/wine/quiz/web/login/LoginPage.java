package nl.wine.quiz.web.login;

import nl.wine.quiz.service.player.PlayerService;
import nl.wine.quiz.web.base.BaseWineQuizPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class LoginPage extends BaseWineQuizPage
{
    @SpringBean
    private PlayerService playerService;

    public LoginPage()
    {
        TextField<String> username = new TextField<>("username", Model.of(""));
        username.add(new UsernameValidator());

        Form<?> form = new Form<Void>("form")
        {
            @Override
            protected void onSubmit()
            {
                final String usernameValue = username.getModelObject();
                if (usernameValue != null)
                {
                    savePlayer(usernameValue);
                }

                //                setResponsePage(StartGamePage.class);
            }
        };

        add(form);
        form.add(username);


    }

    private void savePlayer(String username)
    {
        try
        {
            playerService.savePlayer(username);
        }
        catch (Throwable throwable)
        {
            throwable.printStackTrace();
        }
    }
}
