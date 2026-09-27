package ru.urfu.MySecondTestAppSpringBoot.service;

import org.springframework.validation.BindingResult;
import ru.urfu.MySecondTestAppSpringBoot.exception.UnsupportedCodeException;
import ru.urfu.MySecondTestAppSpringBoot.exception.ValidationFailedException;

public interface ValidationService {

    void isValid(BindingResult bindingResult) throws ValidationFailedException;

    void checkUid(String uid) throws UnsupportedCodeException;
}
