package com.doran.utils.sens;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class Naver_Sens_V2Test {
    // @Autowired
    // Naver_Sens_V2 naverSensV2;

    @Autowired
    Naver_Sens_V2 naverSensV21;

    @BeforeEach
    public void init() {
     naverSensV21 = new Naver_Sens_V2();
    }

    @Test
    public void 문자_인증_테스트() {
        //Naver_Sens_V2 naverSensV2 = new Naver_Sens_V2();
        naverSensV21.send_msg("01091235632", null, MessageType.MODEL);
    }

}