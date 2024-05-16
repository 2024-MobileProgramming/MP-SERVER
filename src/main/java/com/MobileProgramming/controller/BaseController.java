package com.MobileProgramming.controller;

import com.MobileProgramming.domain.User;
import com.MobileProgramming.domain.UserMission;
import com.MobileProgramming.repository.JPA.JPAUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@Transactional
public class BaseController {
    private final JPAUserRepository jpaUserRepository;

    public BaseController(JPAUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

}
