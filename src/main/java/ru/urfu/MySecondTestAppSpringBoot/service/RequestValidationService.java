package ru.urfu.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import ru.urfu.MySecondTestAppSpringBoot.exception.UnsupportedCodeException;
import ru.urfu.MySecondTestAppSpringBoot.exception.ValidationFailedException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RequestValidationService implements ValidationService {

    private static final String UNSUPPORTED_UID = "123";

    @Override
    public void isValid(BindingResult bindingResult) throws ValidationFailedException {
        if (bindingResult.hasErrors()) {
            for (FieldError error : bindingResult.getFieldErrors()) {
                log.error("ошибка в поле '{}': {} (передано значение: {})",
                        error.getField(), error.getDefaultMessage(), error.getRejectedValue());
            }
            throw new ValidationFailedException(bindingResult.getFieldError().getDefaultMessage());
        }
    }

    @Override
    public void checkUid(String uid) throws UnsupportedCodeException {
        if (UNSUPPORTED_UID.equals(uid)) {
            log.error("передан неподдерживаемый uid = {}", uid);
            throw new UnsupportedCodeException("Значение uid = " + UNSUPPORTED_UID + " не поддерживается");
        }
    }
}
