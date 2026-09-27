package ru.urfu.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import ru.urfu.MySecondTestAppSpringBoot.exception.UnsupportedCodeException;
import ru.urfu.MySecondTestAppSpringBoot.exception.ValidationFailedException;

@Service
public class RequestValidationService implements ValidationService {

    private static final String UNSUPPORTED_UID = "123";

    @Override
    public void isValid(BindingResult bindingResult) throws ValidationFailedException {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult.getFieldError().toString());
        }
    }

    @Override
    public void checkUid(String uid) throws UnsupportedCodeException {
        if (UNSUPPORTED_UID.equals(uid)) {
            throw new UnsupportedCodeException("Значение uid = " + UNSUPPORTED_UID + " не поддерживается");
        }
    }
}
