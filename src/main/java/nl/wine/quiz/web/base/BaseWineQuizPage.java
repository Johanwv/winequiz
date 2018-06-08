package nl.wine.quiz.web.base;

import org.apache.wicket.markup.html.WebPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseWineQuizPage extends WebPage
{
    private static final Logger LOG = LoggerFactory.getLogger(BaseWineQuizPage.class);

    public BaseWineQuizPage()
    {
        LOG.info(this.getClass().getName() + " is being loaded");
    }
}
