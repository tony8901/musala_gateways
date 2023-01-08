package com.musala.gateways.utils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UtilTest {

    @Autowired
    private Util util;

    @Test
    void ipValidatingTrue() {
        String ip = (int) Math.floor(Math.random() * 255)
            +"."+ (int) Math.floor(Math.random() * 255)
            +"."+ (int) Math.floor(Math.random() * 255)
            +"."+ (int) Math.floor(Math.random() * 255);
        assertThat(util.ipValidating(ip)).isTrue();
    }
}