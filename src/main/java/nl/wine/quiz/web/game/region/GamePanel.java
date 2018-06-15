package nl.wine.quiz.web.game.region;

import nl.wine.quiz.dto.MultipleChoiceQuestions;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import java.util.List;

public class GamePanel extends Panel
{
    public GamePanel(String id, IModel<List<MultipleChoiceQuestions>> model)
    {
        super(id, model);
    }
}
