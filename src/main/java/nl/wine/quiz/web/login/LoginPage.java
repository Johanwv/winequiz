package nl.wine.quiz.web.login;

import nl.wine.quiz.web.base.BaseWineQuizPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;

public class LoginPage extends BaseWineQuizPage
{
    public LoginPage()
    {
        Form form = new Form("loginForm");

        TextField<String> username = new TextField<>("username", Model.of(""));
        form.add(username);

        add(form);
    }
}
