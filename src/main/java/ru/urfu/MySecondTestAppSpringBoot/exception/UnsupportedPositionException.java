package ru.urfu.MySecondTestAppSpringBoot.exception;

public class UnsupportedPositionException extends RuntimeException {

    public UnsupportedPositionException(String message) {
        super(message);
    }
}
