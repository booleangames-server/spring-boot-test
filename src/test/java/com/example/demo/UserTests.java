package com.example.demo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTests {

    private DateTimeFormatter dateTimeFormatter;
    private User user;

    @Before
    public void setUp() {

        dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        user = new User(25042, 5, "mumbi", 101, 301, "KR", 0, 0, LocalDateTime.parse("2019-12-03 14:23:32", dateTimeFormatter),
            new BigDecimal("7385771914381810308"), LocalDateTime.parse("2019-12-03 14:23:32", dateTimeFormatter), false, false,
            LocalDateTime.parse("2019-10-22 15:41:24", dateTimeFormatter), -1);
    }

    @Test
    public void getterTest() {

        assertThat("asn should be 25042L.", user.getAsn(), is(25042L));
        assertThat("platform type should be 5.", user.getPlatformType(), is(5));
        assertThat("site user id should be 'mumbi'.", user.getSiteUserId(), is("mumbi"));
        assertThat("user db index should be 101.", user.getUserDBIndex(), is(101));
        assertThat("log db index should be 301.", user.getLogDBIndex(), is(301));
        assertThat("country code should be 'KR'.", user.getCountryCode(), is("KR"));
        assertThat("market platform type should be 0.", user.getMarketPlatformType(), is(0));
        assertThat("vip level should be 0.", user.getVIPLevel(), is(0));
        assertThat("last login time should be '2019-12-03 14:23:32'.", user.getLastLoginTime(), is(LocalDateTime.parse("2019-12-03 14:23:32", dateTimeFormatter)));
        assertThat("last key should be '7385771914381810308'.", user.getLastKey(), is(new BigDecimal("7385771914381810308")));
        assertThat("last key update time should be '2019-12-03 14:23:32'.", user.getLastKeyUpdateTime(), is(LocalDateTime.parse("2019-12-03 14:23:32", dateTimeFormatter)));
        assertThat("second download shoud be false.", user.hasSecondaryDownload(), is(false));
        assertThat("is deleted should be false.", user.isDeleted(), is(false));
        assertThat("reg date should be '2019-10-22 15:41:24'.", user.getRegDate(), is(LocalDateTime.parse("2019-10-22 15:41:24", dateTimeFormatter)));
        assertThat("np country should be -1.", user.getNPCountry(), is(-1));
    }

    @Test
    public void equalsTest() {

        User sameUser = new User(25042, 5, "mumbi", 101, 301, "KR", 0, 0, LocalDateTime.parse("2019-12-03 14:23:32", dateTimeFormatter),
            new BigDecimal("7385771914381810308"), LocalDateTime.parse("2019-12-03 14:23:32", dateTimeFormatter), false, false,
            LocalDateTime.parse("2019-10-22 15:41:24", dateTimeFormatter), -1);

        User asnUser = new User(25041, 5, "mumbi", 101, 301, "KR", 0, 0, LocalDateTime.parse("2019-12-03 14:23:32", dateTimeFormatter),
            new BigDecimal("7385771914381810308"), LocalDateTime.parse("2019-12-03 14:23:32", dateTimeFormatter), false, false,
            LocalDateTime.parse("2019-10-22 15:41:24", dateTimeFormatter), -1);

        User platformTypeUser = new User(25042, 6, "mumbi", 101, 301, "KR", 0, 0, LocalDateTime.parse("2019-12-03 14:23:32", dateTimeFormatter),
        new BigDecimal("7385771914381810308"), LocalDateTime.parse("2019-12-03 14:23:32", dateTimeFormatter), false, false,
        LocalDateTime.parse("2019-10-22 15:41:24", dateTimeFormatter), -1);

        User siteUserIdUser = new User(25042, 5, "mumbii", 101, 301, "KR", 0, 0, LocalDateTime.parse("2019-12-03 14:23:32", dateTimeFormatter),
            new BigDecimal("7385771914381810308"), LocalDateTime.parse("2019-12-03 14:23:32", dateTimeFormatter), false, false,
            LocalDateTime.parse("2019-10-22 15:41:24", dateTimeFormatter), -1);

        User userDBIndexUser = new User(25042, 5, "mumbi", 102, 301, "KR", 0, 0, LocalDateTime.parse("2019-12-03 14:23:32", dateTimeFormatter),
            new BigDecimal("7385771914381810308"), LocalDateTime.parse("2019-12-03 14:23:32", dateTimeFormatter), false, false,
            LocalDateTime.parse("2019-10-22 15:41:24", dateTimeFormatter), -1);

        User logDBIndexUser = new User(25042, 5, "mumbi", 101, 302, "KR", 0, 0, LocalDateTime.parse("2019-12-03 14:23:32", dateTimeFormatter),
            new BigDecimal("7385771914381810308"), LocalDateTime.parse("2019-12-03 14:23:32", dateTimeFormatter), false, false,
            LocalDateTime.parse("2019-10-22 15:41:24", dateTimeFormatter), -1);

        User countryCodeUser = new User(25042, 5, "mumbi", 101, 301, "US", 0, 0, LocalDateTime.parse("2019-12-03 14:23:32", dateTimeFormatter),
            new BigDecimal("7385771914381810308"), LocalDateTime.parse("2019-12-03 14:23:32", dateTimeFormatter), false, false,
            LocalDateTime.parse("2019-10-22 15:41:24", dateTimeFormatter), -1);

        User marketPlatformTypeUser = new User(25042, 5, "mumbi", 101, 301, "KR", 1, 0, LocalDateTime.parse("2019-12-03 14:23:32", dateTimeFormatter),
            new BigDecimal("7385771914381810308"), LocalDateTime.parse("2019-12-03 14:23:32", dateTimeFormatter), false, false,
            LocalDateTime.parse("2019-10-22 15:41:24", dateTimeFormatter), -1);

        User vipLevelUser = new User(25042, 5, "mumbi", 101, 301, "KR", 0, 1, LocalDateTime.parse("2019-12-03 14:23:32", dateTimeFormatter),
            new BigDecimal("7385771914381810308"), LocalDateTime.parse("2019-12-03 14:23:32", dateTimeFormatter), false, false,
            LocalDateTime.parse("2019-10-22 15:41:24", dateTimeFormatter), -1);

        User lastLoginTimeUser = new User(25042, 5, "mumbi", 101, 301, "KR", 0, 0, LocalDateTime.parse("2019-12-03 14:23:33", dateTimeFormatter),
            new BigDecimal("7385771914381810308"), LocalDateTime.parse("2019-12-03 14:23:32", dateTimeFormatter), false, false,
            LocalDateTime.parse("2019-10-22 15:41:24", dateTimeFormatter), -1);

        User lastKeyUser = new User(25042, 5, "mumbi", 101, 301, "KR", 0, 0, LocalDateTime.parse("2019-12-03 14:23:32", dateTimeFormatter),
            new BigDecimal("7385771914381810309"), LocalDateTime.parse("2019-12-03 14:23:32", dateTimeFormatter), false, false,
            LocalDateTime.parse("2019-10-22 15:41:24", dateTimeFormatter), -1);

        User lastKeyUpdateTimeUser = new User(25042, 5, "mumbi", 101, 301, "KR", 0, 0, LocalDateTime.parse("2019-12-03 14:23:32", dateTimeFormatter),
            new BigDecimal("7385771914381810308"), LocalDateTime.parse("2019-12-03 14:23:31", dateTimeFormatter), false, false,
            LocalDateTime.parse("2019-10-22 15:41:24", dateTimeFormatter), -1);

        User secondDownloadUser = new User(25042, 5, "mumbi", 101, 301, "KR", 0, 0, LocalDateTime.parse("2019-12-03 14:23:32", dateTimeFormatter),
            new BigDecimal("7385771914381810308"), LocalDateTime.parse("2019-12-03 14:23:32", dateTimeFormatter), true, false,
            LocalDateTime.parse("2019-10-22 15:41:24", dateTimeFormatter), -1);

        User isDeletedUser = new User(25042, 5, "mumbi", 101, 301, "KR", 0, 0, LocalDateTime.parse("2019-12-03 14:23:32", dateTimeFormatter),
            new BigDecimal("7385771914381810308"), LocalDateTime.parse("2019-12-03 14:23:32", dateTimeFormatter), false, true,
            LocalDateTime.parse("2019-10-22 15:41:24", dateTimeFormatter), -1);

        User regDateUer = new User(25042, 5, "mumbi", 101, 301, "KR", 0, 0, LocalDateTime.parse("2019-12-03 14:23:32", dateTimeFormatter),
            new BigDecimal("7385771914381810308"), LocalDateTime.parse("2019-12-03 14:23:32", dateTimeFormatter), false, false,
            LocalDateTime.parse("2019-10-22 15:41:23", dateTimeFormatter), -1);

        User npCountryUser = new User(25042, 5, "mumbi", 101, 301, "KR", 0, 0, LocalDateTime.parse("2019-12-03 14:23:32", dateTimeFormatter),
            new BigDecimal("7385771914381810308"), LocalDateTime.parse("2019-12-03 14:23:32", dateTimeFormatter), false, false,
            LocalDateTime.parse("2019-10-22 15:41:24", dateTimeFormatter), 0);
        
        assertThat("user should be equals to same user.", user, equalTo(sameUser));
        assertThat("user should be not equals to asn user.", user, not(equalTo(asnUser)));
        assertThat("user should be not equals to platform type user.", user, not(equalTo(platformTypeUser)));
        assertThat("user should be not equals to site user id user.", user, not(equalTo(siteUserIdUser)));
        assertThat("user should be not equals to user db index user.", user, not(equalTo(userDBIndexUser)));
        assertThat("user should be not equals to log db index user.", user, not(equalTo(logDBIndexUser)));
        assertThat("user should be not equals to country code user.", user, not(equalTo(countryCodeUser)));
        assertThat("user should be not equals to market platform type user.", user, not(equalTo(marketPlatformTypeUser)));
        assertThat("user should be not equals to vip level user.", user, not(equalTo(vipLevelUser)));
        assertThat("user should be not equals to last login time user.", user, not(equalTo(lastLoginTimeUser)));
        assertThat("user should be not equals to last key user.", user, not(equalTo(lastKeyUser)));
        assertThat("user should be not equals to last key update time user.", user, not(equalTo(lastKeyUpdateTimeUser)));
        assertThat("user should be not equals to second download user.", user, not(equalTo(secondDownloadUser)));
        assertThat("user should be not equals to is deleted user.", user, not(equalTo(isDeletedUser)));
        assertThat("user should be not equals to reg date user.", user, not(equalTo(regDateUer)));
        assertThat("user should be not equals to np country user.", user, not(equalTo(npCountryUser)));
    }
    
}