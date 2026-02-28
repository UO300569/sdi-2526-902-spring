package com.uniovi.sdi.sdi2526902spring.validators;

import com.uniovi.sdi.sdi2526902spring.entities.Mark;
import com.uniovi.sdi.sdi2526902spring.services.MarksService;
import com.uniovi.sdi.sdi2526902spring.services.UsersService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class MarkValidator implements Validator {

    private final MarksService marksService;

    public MarkValidator(MarksService marksService) {
        this.marksService = marksService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Mark.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Mark mark = (Mark) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "Error.empty");
        if (mark.getDescription() != null && mark.getDescription().length() < 20) {
            errors.rejectValue("description", "Error.addmark.description.length");
        }

        if (mark.getScore() == null) {
            errors.rejectValue("score", "Error.empty");
        } else if (mark.getScore() < 0 || mark.getScore() > 10) {
            errors.rejectValue("score", "Error.addmark.score.range");
        }

        // Validación de usuario (Para evitar el NullPointerException en el futuro)
        if (mark.getUser() == null || mark.getUser().getId() == null) {
            errors.rejectValue("user", "Error.empty");
        }
    }
}