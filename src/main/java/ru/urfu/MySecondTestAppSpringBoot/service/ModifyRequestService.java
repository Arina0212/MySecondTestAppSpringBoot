package ru.urfu.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.urfu.MySecondTestAppSpringBoot.model.Request;

@Service
public interface ModifyRequestService {

    void modify(Request request);
}
