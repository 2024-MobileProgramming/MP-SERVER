package com.MobileProgramming.ServiceTest;

import com.MobileProgramming.service.BaseService;
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
    @DisplayName("매일 팀 빌딩 및 미션 부여")
    void scheduling() {
        baseService.scheduling();
    }
}
