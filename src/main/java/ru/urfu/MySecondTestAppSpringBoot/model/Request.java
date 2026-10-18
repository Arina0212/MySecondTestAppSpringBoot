package ru.urfu.MySecondTestAppSpringBoot.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Request {

    /**
     * Уникальный идентификатор сообщения
     */
    @NotBlank(message = "Поле uid обязательно для заполнения")
    @Size(max = 32, message = "Длина поля uid не должна превышать 32 символа")
    private String uid;

    /**
     * Уникальный идентификатор операции
     */
    @NotBlank(message = "Поле operationUid обязательно для заполнения")
    @Size(max = 32, message = "Длина поля operationUid не должна превышать 32 символа")
    private String operationUid;

    /**
     * Имя системы отправителя
     */
    private Systems systemName;

    /**
     * Время создания сообщения
     */
    @NotBlank(message = "Поле systemTime обязательно для заполнения")
    private String systemTime;

    /**
     * Наименование ресурса
     */
    private String source;

    /**
     * Должность сотрудника
     */
    private Positions position;

    /**
     * Оклад сотрудника
     */
    private Double salary;

    /**
     * Бонусный коэффициент сотрудника
     */
    private Double bonus;

    /**
     * Количество отработанных дней
     */
    private Integer workDays;

    /**
     * Уникальный идентификатор коммуникации
     */
    @Min(value = 1, message = "Поле communicationId не может быть меньше 1")
    @Max(value = 100000, message = "Поле communicationId не может быть больше 100000")
    private int communicationId;

    /**
     * Уникальный идентификатор шаблона
     */
    private int templateId;

    /**
     * Код продукта
     */
    private int productCode;

    /**
     * Смс код
     */
    private int smsCode;

    @Override
    public String toString() {
        return "{" +
                "uid='" + uid + '\'' +
                ", operationUid='" + operationUid + '\'' +
                ", systemName='" + systemName + '\'' +
                ", systemTime='" + systemTime + '\'' +
                ", source='" + source + '\'' +
                ", position=" + position +
                ", salary=" + salary +
                ", bonus=" + bonus +
                ", workDays=" + workDays +
                ", communicationId=" + communicationId +
                ", templateId=" + templateId +
                ", productCode=" + productCode +
                ", smsCode=" + smsCode +
                '}';
    }
}
