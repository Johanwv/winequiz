package nl.wine.quiz.util;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import java.io.Serializable;
import java.util.List;

public class ModelUtil
{
    private ModelUtil()
    {
    }

    public static  <T extends Serializable> Model createModel(T object)
    {
        return new Model(object);
    }

    public static <T extends Serializable> IModel<List<T>> listIModel(List<T> list)
    {
        return Model.ofList(list);
    }
}
