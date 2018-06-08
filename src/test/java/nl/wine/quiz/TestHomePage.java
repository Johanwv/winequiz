package nl.wine.quiz;

import nl.wine.quiz.web.WineQuizApplication;
import nl.wine.quiz.web.game.BaseGamePage;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

/**
 * Simple test using the WicketTester
 */
public class TestHomePage
{
    private WicketTester tester;

    @Before
    public void setUp()
    {
        tester = new WicketTester(new WineQuizApplication());
    }

    @Test
    public void homepageRendersSuccessfully()
    {
        //start and render the test page
        tester.startPage(BaseGamePage.class);

        //assert rendered page class
        tester.assertRenderedPage(BaseGamePage.class);
    }
}
