package ru.urfu.MySecondTestAppSpringBoot.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {

    /**
     * Уникальный идентификатор сообщения
     */
    private String uid;

    /**
     * Уникальный идентификатор операции
     */
    private String operationUid;

    /**
     * Время формирования ответа
     */
    private String systemTime;

    /**
     * Результат обработки запроса
     */
    private Codes code;

    /**
     * Рассчитанная годовая премия
     */
    private Double annualBonus;

    /**
     * Рассчитанная квартальная премия, заполняется только для управленцев
     */
    private Double quarterlyBonus;

    /**
     * Код ошибки
     */
    private ErrorCodes errorCode;

    /**
     * Сообщение об ошибке
     */
    private ErrorMessages errorMessage;
}
