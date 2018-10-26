package nl.wine.quiz.web.login;

import nl.wine.quiz.web.base.BaseWineQuizPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;

public class LoginPage extends BaseWineQuizPage
{
    public LoginPage()
    {
        TextField<String> username = new TextField<>("username", Model.of(""));

        Form<?> form = new Form<Void>("form")
        {
            @Override
            protected void onSubmit()
            {
                final String usernameValue = username.getModelObject();
//                setResponsePage(StartGamePage.class);
            }
        };

        form.add(username);

        add(form);
    }
}
