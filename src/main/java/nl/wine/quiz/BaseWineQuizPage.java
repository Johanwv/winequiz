package nl.wine.quiz;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

public class BaseWineQuizPage extends WebPage
{
	public BaseWineQuizPage()
	{
		add(new Label("label", "Hello world"));
	}
}
