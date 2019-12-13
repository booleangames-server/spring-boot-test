package com.example.demo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTests {

    @Autowired    
    private UserRepository userRepository;

    private User expectedUser;

    @Before
    public void setUp() {

        DateTimeFormatter ldtFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        expectedUser = new User(25042, 5, "mumbi", 101, 301, "KR", 0, 0, LocalDateTime.parse("2019-12-03 14:23:32", ldtFormat),
            new BigDecimal("7385771914381810308"), LocalDateTime.parse("2019-12-03 14:23:32", ldtFormat), false, false, LocalDateTime.parse("2019-10-22 15:41:24", ldtFormat), -1);

    }

    @Test
    public void findUserByPlatformTypeAndSiteUserIdTest() {

        User user = userRepository.findUser(5, "mumbi");
        
        assertThat("find user by platform type and site user id return user.", user, is(expectedUser));
    }

    @Test
    public void findUserByAsnTest() {
        User user = userRepository.findUser(25042L);

        assertThat("find user by asn return user.", user, is(expectedUser));
    }
}