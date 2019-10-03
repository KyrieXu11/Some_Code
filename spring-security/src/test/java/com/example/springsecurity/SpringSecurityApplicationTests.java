package com.example.springsecurity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringSecurityApplicationTests {

    @Test
    public void contextLoads() {
        for (int i = 0; i < 10; i++) {
            BCryptPasswordEncoder bcp = new BCryptPasswordEncoder();
            System.out.println(bcp.encode("wdnmd"));
        }
    }
}
