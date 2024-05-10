package com.MobileProgramming.ServiceTest;

import com.MobileProgramming.service.BaseService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class team_formation_test {
    @Autowired
    BaseService baseService;

    @Test
    @DisplayName("팀 갱신하기")
    void formation_test() {
        baseService.teamformation();
    }

    @Test
    @DisplayName("되라...제발..")
    void pleas() {
        baseService.scheduling();
    }
}
