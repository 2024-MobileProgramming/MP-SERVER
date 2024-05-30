package com.MobileProgramming.controller;

import com.MobileProgramming.repository.JPA.JPAUserRepository;
import com.MobileProgramming.serviceImpl.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Transactional
public class BaseController {
    private final JPAUserRepository jpaUserRepository;
    private final BaseServiceImpl baseService;

    public BaseController(JPAUserRepository jpaUserRepository, BaseServiceImpl baseService) {
        this.baseService = baseService;
        this.jpaUserRepository = jpaUserRepository;
    }


    @GetMapping("/execute")
    public void execute() {
        baseService.scheduling();
    }
}