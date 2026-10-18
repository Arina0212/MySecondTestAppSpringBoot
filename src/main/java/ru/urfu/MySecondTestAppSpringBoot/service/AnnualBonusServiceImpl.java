package ru.urfu.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.urfu.MySecondTestAppSpringBoot.model.Positions;

import java.time.Year;

@Service
public class AnnualBonusServiceImpl implements AnnualBonusService {

    @Override
    public double calculate(Positions positions, double salary, double bonus, int workDays) {
        int daysInYear = Year.now().length();
        return salary * bonus * daysInYear * positions.getPositionCoefficient() / workDays;
    }
}
