package ru.urfu.MySecondTestAppSpringBoot.service;

import org.junit.jupiter.api.Test;
import ru.urfu.MySecondTestAppSpringBoot.model.Positions;

import static org.assertj.core.api.Assertions.assertThat;

class AnnualBonusServiceImplTest {

    private final AnnualBonusService annualBonusService = new AnnualBonusServiceImpl();

    @Test
    void calculate() {

        //given
        Positions position = Positions.HR;
        double salary = 100000.00;
        double bonus = 2.0;
        int workDays = 243;

        //when
        double result = annualBonusService.calculate(position, salary, bonus, workDays);

        //then
        double expected = 360493.8271604938;
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void calculateForManager() {

        //given
        Positions position = Positions.CTO;
        double salary = 100000.00;
        double bonus = 2.0;
        int workDays = 243;

        //when
        double result = annualBonusService.calculate(position, salary, bonus, workDays);

        //then
        assertThat(result).isGreaterThan(
                annualBonusService.calculate(Positions.HR, salary, bonus, workDays));
    }
}
