package ru.urfu.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.urfu.MySecondTestAppSpringBoot.exception.UnsupportedPositionException;
import ru.urfu.MySecondTestAppSpringBoot.model.Positions;

import java.time.Year;

@Service
public class QuarterlyBonusServiceImpl implements QuarterlyBonusService {

    private static final int QUARTERS_IN_YEAR = 4;

    @Override
    public double calculate(Positions positions, double salary, double bonus, int workDays) {
        if (!positions.isManager()) {
            throw new UnsupportedPositionException(
                    "Квартальная премия рассчитывается только для управленцев, должность "
                            + positions + " управленческой не является");
        }

        double daysInQuarter = (double) Year.now().length() / QUARTERS_IN_YEAR;
        return salary * bonus * daysInQuarter * positions.getPositionCoefficient() / workDays;
    }
}
