package ru.urfu.MySecondTestAppSpringBoot.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ErrorMessages {

    EMPTY(""),

    VALIDATION("Ошибка валидации"),

    UNSUPPORTED("Не поддерживаемая ошибка"),

    UNKNOW("Произошла непредвиденная ошибка");

    private final String description;

    ErrorMessages(String description){
        this.description = description;
    }

    @JsonValue
    public String getName(){
        return description;
    }
}
