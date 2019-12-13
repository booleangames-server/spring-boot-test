package com.example.demo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    private UserService userService;
    private User repositoryUser;

    @Before
    public void setUp() {        
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        userService = new UserServiceImpl(userRepository);
        repositoryUser = new User(25042, 5, "mumbi", 101, 301, "KR", 0, 0, LocalDateTime.parse("2019-12-03 14:23:32", dateTimeFormatter),
            new BigDecimal("7385771914381810308"), LocalDateTime.parse("2019-12-03 14:23:32", dateTimeFormatter), false, false,
            LocalDateTime.parse("2019-10-22 15:41:24", dateTimeFormatter), -1);
    }

    @Test
    public void getUserByPlatformTypeAndSiteUserIdTest() {
        given(userRepository.findUser(5, "mumbi"))
            .willReturn(repositoryUser);
        
        final User serviceUser = userService.getUser(5, "mumbi");

        verify(userRepository, times(1)).findUser(5, "mumbi");

        assertThat("get user by platform type and site user id return user.", serviceUser, is(repositoryUser));
    }

    @Test
    public void getUserByAsnTest() {
        given(userRepository.findUser(25042L))
            .willReturn(repositoryUser);

        final User serviceUser = userService.getUser(25042L);

        verify(userRepository, times(1)).findUser(25042L);

        assertThat("get user by asn return user", serviceUser, is(repositoryUser));
    }
    
}