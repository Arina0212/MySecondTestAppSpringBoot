package ru.urfu.MySecondTestAppSpringBoot.service;

import org.junit.jupiter.api.Test;
import ru.urfu.MySecondTestAppSpringBoot.exception.UnsupportedPositionException;
import ru.urfu.MySecondTestAppSpringBoot.model.Positions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class QuarterlyBonusServiceImplTest {

    private final QuarterlyBonusService quarterlyBonusService = new QuarterlyBonusServiceImpl();

    @Test
    void calculate() {

        //given
        Positions position = Positions.TL;
        double salary = 100000.00;
        double bonus = 2.0;
        int workDays = 60;

        //when
        double result = quarterlyBonusService.calculate(position, salary, bonus, workDays);

        //then
        double expected = 790833.3333333334;
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void calculateThrowsExceptionForNonManager() {

        //given
        Positions position = Positions.DEV;
        double salary = 100000.00;
        double bonus = 2.0;
        int workDays = 60;

        //when, then
        assertThatThrownBy(() -> quarterlyBonusService.calculate(position, salary, bonus, workDays))
                .isInstanceOf(UnsupportedPositionException.class)
                .hasMessageContaining("только для управленцев");
    }
}
