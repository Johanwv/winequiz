package nl.wine.quiz.web;

import nl.wine.quiz.web.login.LoginPage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
 *
 * @see nl.wine.quiz.Start#main(String[])
 */
public class WineQuizApplication extends WebApplication
{
    /**
     * @see org.apache.wicket.Application#getHomePage()
     */
    @Override
    public Class<? extends WebPage> getHomePage()
    {
        return LoginPage.class;
    }

    /**
     * @see org.apache.wicket.Application#init()
     */
    @Override
    public void init()
    {
        super.init();

        initSpring();
    }

    private void initSpring()
    {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        getComponentInstantiationListeners().add(new SpringComponentInjector(this, appContext));
    }
}
