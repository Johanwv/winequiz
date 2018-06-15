package nl.wine.quiz.util;

import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import java.io.Serializable;

public class ModelUtil
{
    private ModelUtil()
    {
    }

    public static  <T extends Serializable> Model createModel(T object)
    {
        return new Model(object);
    }
}
