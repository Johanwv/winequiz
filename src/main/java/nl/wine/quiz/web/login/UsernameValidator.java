package nl.wine.quiz.web.login;

import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class UsernameValidator implements IValidator<String>
{
    List<String> existingUsernames = Arrays.asList("Johan", "Femke");

    public void validate(IValidatable<String> validatable)
    {
        String chosenUserName = validatable.getValue();

        if (existingUsernames.contains(chosenUserName))
        {
            ValidationError error = new ValidationError("Invalid name");
            Random random = new Random();

            error.setVariable("suggestedUserName",
                    validatable.getValue() + random.nextInt());
            validatable.error(error);
        }
    }
}
