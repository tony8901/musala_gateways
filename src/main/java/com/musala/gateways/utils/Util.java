package com.musala.gateways.utils;

import org.springframework.http.ResponseEntity;

public class Util {
    public Util() {
    }

    public boolean ipValidating(String ip){
        boolean b = true;
        String[] strings = ip.split("\\.");
        if (strings.length != 4) {
            b = false;
        }
        try {
            for (String s : strings) {
                int i = Integer.parseInt(s);
                if (i < 0 || i > 255) {
                    b = false;
                }
            }
        } catch (NumberFormatException e){
            ResponseEntity.badRequest().build();
        }

        return b;
    }
}
