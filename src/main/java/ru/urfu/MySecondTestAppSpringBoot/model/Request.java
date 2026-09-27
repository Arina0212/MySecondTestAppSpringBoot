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

    @NotBlank(message = "Поле uid обязательно для заполнения")
    @Size(max = 32, message = "Длина поля uid не должна превышать 32 символа")
    private String uid;

    @NotBlank(message = "Поле operationUid обязательно для заполнения")
    @Size(max = 32, message = "Длина поля operationUid не должна превышать 32 символа")
    private String operationUid;

    private String systemName;

    @NotBlank(message = "Поле systemTime обязательно для заполнения")
    private String systemTime;

    private String source;

    @Min(value = 1, message = "Поле communicationId не может быть меньше 1")
    @Max(value = 100000, message = "Поле communicationId не может быть больше 100000")
    private int communicationId;

    private int templateId;

    private int productCode;

    private int smsCode;
}
