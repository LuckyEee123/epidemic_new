package com.mai.epidemic;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
public class PwdTest {

    @Test
    public void bcpTest() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("123456"));
        System.out.println(encoder.matches("123456", "$2a$10$uGOX./4b5ZXdi499qLe/Tu68.rt4dUO/SDUS6sFqJuNr50FrNH09S"));
    }

}
