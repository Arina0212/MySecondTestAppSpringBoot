package ru.urfu.MySecondTestAppSpringBoot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.urfu.MySecondTestAppSpringBoot.model.Response;
import ru.urfu.MySecondTestAppSpringBoot.util.DateTimeUtil;

import java.util.Date;

@Slf4j
@Service
@Qualifier("ModifySystemTimeResponseService")
public class ModifySystemTimeResponseService implements ModifyResponseService{

    @Override
    public Response modify(Response response) {

        log.info("старое systemTime: {}", response.getSystemTime());
        response.setSystemTime(DateTimeUtil.getCustomFormat().format(new Date()));
        log.info("новое systemTime: {}", response.getSystemTime());

        return response;
    }
}
