package ru.urfu.MySecondTestAppSpringBoot.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.urfu.MySecondTestAppSpringBoot.exception.UnsupportedCodeException;
import ru.urfu.MySecondTestAppSpringBoot.exception.ValidationFailedException;
import ru.urfu.MySecondTestAppSpringBoot.model.*;
import ru.urfu.MySecondTestAppSpringBoot.service.AnnualBonusService;
import ru.urfu.MySecondTestAppSpringBoot.service.ModifyRequestService;
import ru.urfu.MySecondTestAppSpringBoot.service.ModifyResponseService;
import ru.urfu.MySecondTestAppSpringBoot.service.QuarterlyBonusService;
import ru.urfu.MySecondTestAppSpringBoot.service.ValidationService;
import ru.urfu.MySecondTestAppSpringBoot.util.DateTimeUtil;

import java.util.Date;

@Slf4j
@RestController
public class MyController {

    private final ValidationService validationService;
    private final ModifyResponseService modifyResponseService;
    private final ModifyRequestService modifySourceRequestService;
    private final ModifyRequestService modifySystemNameRequestService;
    private final AnnualBonusService annualBonusService;
    private final QuarterlyBonusService quarterlyBonusService;

    @Autowired
    public MyController(ValidationService validationService,
                        @Qualifier("ModifySystemTimeResponseService") ModifyResponseService modifyResponseService,
                        @Qualifier("ModifySourceRequestService") ModifyRequestService modifySourceRequestService,
                        @Qualifier("ModifySystemNameRequestService") ModifyRequestService modifySystemNameRequestService,
                        AnnualBonusService annualBonusService,
                        QuarterlyBonusService quarterlyBonusService) {
        this.validationService = validationService;
        this.modifyResponseService = modifyResponseService;
        this.modifySourceRequestService = modifySourceRequestService;
        this.modifySystemNameRequestService = modifySystemNameRequestService;
        this.annualBonusService = annualBonusService;
        this.quarterlyBonusService = quarterlyBonusService;
    }

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request,
                                             BindingResult bindingResult) {
        request.setSystemTime(DateTimeUtil.getCustomFormat().format(new Date()));
        log.info("request: {}", request);

        Response response = buildResponse(request);

        try {
            validationService.isValid(bindingResult);
            validationService.checkUid(request.getUid());
            log.info("валидация request прошла успешно");
        } catch (ValidationFailedException e) {
            return error(response, ErrorCodes.VALIDATION_EXCEPTION, ErrorMessages.VALIDATION,
                    HttpStatus.BAD_REQUEST, e);
        } catch (UnsupportedCodeException e) {
            return error(response, ErrorCodes.UNSUPPORTED_EXCEPTION, ErrorMessages.UNSUPPORTED,
                    HttpStatus.BAD_REQUEST, e);
        } catch (Exception e) {
            return error(response, ErrorCodes.UNKNOWN_EXCEPTION, ErrorMessages.UNKNOW,
                    HttpStatus.INTERNAL_SERVER_ERROR, e);
        }

        calculateBonuses(request, response);

        modifyResponseService.modify(response);
        modifySourceRequestService.modify(request);
        modifySystemNameRequestService.modify(request);

        log.info("итоговый response: {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private Response buildResponse(Request request) {
        Response response = Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(DateTimeUtil.getCustomFormat().format(new Date()))
                .code(Codes.SUCCESS)
                .errorCode(ErrorCodes.EMPTY)
                .errorMessage(ErrorMessages.EMPTY)
                .build();
        log.info("response создан: {}", response);
        return response;
    }

    private void calculateBonuses(Request request, Response response) {
        if (request.getPosition() == null || request.getSalary() == null
                || request.getBonus() == null || request.getWorkDays() == null) {
            log.info("данные для расчёта премии переданы не полностью, расчёт пропущен");
            return;
        }

        Positions position = request.getPosition();

        double annualBonus = annualBonusService.calculate(
                position, request.getSalary(), request.getBonus(), request.getWorkDays());
        response.setAnnualBonus(annualBonus);
        log.info("годовая премия для должности {}: {}", position, annualBonus);

        if (!position.isManager()) {
            log.info("должность {} не является управленческой, квартальная премия не рассчитывается", position);
            return;
        }

        double quarterlyBonus = quarterlyBonusService.calculate(
                position, request.getSalary(), request.getBonus(), request.getWorkDays());
        response.setQuarterlyBonus(quarterlyBonus);
        log.info("квартальная премия для должности {}: {}", position, quarterlyBonus);
    }

    private ResponseEntity<Response> error(Response response, ErrorCodes errorCode,
                                           ErrorMessages errorMessage, HttpStatus status, Exception e) {
        response.setCode(Codes.FAILED);
        response.setErrorCode(errorCode);
        response.setErrorMessage(errorMessage);
        log.error("ошибка обработки запроса: {}. response: {}", e.getMessage(), response);
        return new ResponseEntity<>(response, status);
    }
}
