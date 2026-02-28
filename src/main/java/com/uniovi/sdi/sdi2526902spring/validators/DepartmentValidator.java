package com.uniovi.sdi.sdi2526902spring.validators;

import com.uniovi.sdi.sdi2526902spring.entities.Department;
import com.uniovi.sdi.sdi2526902spring.entities.Mark;
import com.uniovi.sdi.sdi2526902spring.services.DepartmentService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class DepartmentValidator implements Validator {
    private final DepartmentService departmentService;

    public DepartmentValidator(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Mark.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Department dept = (Department) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "Error.addDepartment.name.blank");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "faculty", "Error.addDepartment.faculty.blank");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "telephone", "Error.addDepartment.telephone.blank");

        if (dept.getCode() == null || dept.getCode().length() != 9) {
            errors.rejectValue("code", "Error.addDepartment.code.length");
        }

        if (dept.getCode() != null && !dept.getCode().isEmpty()) {
            char lastChar = dept.getCode().charAt(dept.getCode().length() - 1);
            if (!Character.isLetter(lastChar)) {
                errors.rejectValue("code", "Error.addDepartment.code.last");
            }
        }

        if (departmentService.getDepartmentByCode(dept.getCode()) != null) {
            errors.rejectValue("code", "Error.addDepartment.code.unique");
        }
    }
}
