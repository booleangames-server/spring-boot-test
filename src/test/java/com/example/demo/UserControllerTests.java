package com.example.demo;

import org.hamcrest.Description;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

import static org.hamcrest.Matchers.is;
import org.hamcrest.BaseMatcher;;

interface Converter<T> {
    public T convert(Object u);
}

class ConvertingMatcher<T> extends BaseMatcher<T> {

    private final T t;
    //private final Converter<T> converter;
    private final Function<Object, T> converter;

    // public ConvertingMatcher(T t, Converter<T> converter) {
    public ConvertingMatcher(T t, Function<Object, T> converter) {
        this.t = t;
        this.converter = converter;
    }
    
    @Override
    public boolean matches(Object actual) {
        T result = converter.apply(actual);
        
        return t.equals(result);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(t.toString());
    }
}

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTests {

    @Autowired
    private MappingJackson2HttpMessageConverter jackson2HttpMessageConverter; 

    private MockMvc mockMvc;

    @MockBean
    UserController userController;

    private User controllerUser;

    private static org.hamcrest.Matcher<BigDecimal> isLongEqualsToBigDecimal(BigDecimal value) {        
        return new ConvertingMatcher<BigDecimal>(value, (obj) -> BigDecimal.valueOf((long)obj));
    }

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders
            .standaloneSetup(userController)
            .setMessageConverters(this.jackson2HttpMessageConverter) // Important!
            .build();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        controllerUser = new User(25042, 5, "mumbi", 101, 301, "KR", 0, 0, LocalDateTime.parse("2019-12-03 14:23:32", dateTimeFormatter),
            new BigDecimal("7385771914381810308"), LocalDateTime.parse("2019-12-03 14:23:32", dateTimeFormatter), false, false,
            LocalDateTime.parse("2019-10-22 15:41:24", dateTimeFormatter), -1);
    }

    @Test
    public void getUserByPlatformTypeAndSiteUserIdTest() throws Exception {

        given(userController.getUser(5, "mumbi"))
            .willReturn(Mono.fromCallable(() -> controllerUser).subscribeOn(Schedulers.elastic()));

        MvcResult mvcResult = mockMvc.perform(get("/users/5/mumbi"))
            .andExpect(request().asyncStarted())
            .andReturn();

        mockMvc.perform(asyncDispatch(mvcResult))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))            
            .andExpect(jsonPath("$.asn").value(controllerUser.getAsn()))            
            .andExpect(jsonPath("$.lastLoginTime", is(controllerUser.getLastLoginTime().toString())))
            .andExpect(jsonPath("$.siteUserId", is(controllerUser.getSiteUserId())))
            .andExpect(jsonPath("$.userDBIndex", is(controllerUser.getUserDBIndex())))
            .andExpect(jsonPath("$.logDBIndex", is(controllerUser.getLogDBIndex())))
            .andExpect(jsonPath("$.countryCode", is(controllerUser.getCountryCode())))
            .andExpect(jsonPath("$.marketPlatformType", is(controllerUser.getMarketPlatformType())))
            .andExpect(jsonPath("$.lastLoginTime", is(controllerUser.getLastLoginTime().toString())))
            .andExpect(jsonPath("$.lastKey", isLongEqualsToBigDecimal(controllerUser.getLastKey())))
            .andExpect(jsonPath("$.lastKeyUpdateTime", is(controllerUser.getLastKeyUpdateTime().toString())))
            .andExpect(jsonPath("$.regDate", is(controllerUser.getRegDate().toString())))
            .andExpect(jsonPath("$.viplevel", is(controllerUser.getVIPLevel())))
            .andExpect(jsonPath("$.npcountry", is(controllerUser.getNPCountry())))
            .andExpect(jsonPath("$.platformType", is(controllerUser.getPlatformType())))
            .andExpect(jsonPath("$.isDeleted", is(controllerUser.isDeleted())))
            .andExpect(jsonPath("$.secondaryDownload", is(controllerUser.hasSecondaryDownload())))
            .andDo(print())
            .andReturn();

            // {"asn":25042,"siteUserId":"mumbi","userDBIndex":101,"logDBIndex":301,"countryCode":"KR",
            // "marketPlatformType":0,"lastLoginTime":"2019-12-03T14:23:32","lastKey":7385771914381810308,
            // "lastKeyUpdateTime":"2019-12-03T14:23:32","regDate":"2019-10-22T15:41:24","viplevel":0,"npcountry":-1,
            // "plantformType":5,"isDeleted":false,"secondaryDownload":false}

        verify(userController, times(1)).getUser(5, "mumbi");
    }

    @Test
    public void getUserByAsnTest() throws Exception {
        given(userController.getUser(25042L))
            .willReturn(Mono.fromCallable(() -> controllerUser).subscribeOn(Schedulers.elastic()));

        MvcResult mvcResult = mockMvc.perform(get("/users/{asn}", 25042L))
            .andExpect(request().asyncStarted())
            .andReturn();

        mockMvc.perform(asyncDispatch(mvcResult))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))            
            .andExpect(jsonPath("$.asn").value(controllerUser.getAsn()))            
            .andExpect(jsonPath("$.lastLoginTime", is(controllerUser.getLastLoginTime().toString())))
            .andExpect(jsonPath("$.siteUserId", is(controllerUser.getSiteUserId())))
            .andExpect(jsonPath("$.userDBIndex", is(controllerUser.getUserDBIndex())))
            .andExpect(jsonPath("$.logDBIndex", is(controllerUser.getLogDBIndex())))
            .andExpect(jsonPath("$.countryCode", is(controllerUser.getCountryCode())))
            .andExpect(jsonPath("$.marketPlatformType", is(controllerUser.getMarketPlatformType())))
            .andExpect(jsonPath("$.lastLoginTime", is(controllerUser.getLastLoginTime().toString())))
            .andExpect(jsonPath("$.lastKey", isLongEqualsToBigDecimal(controllerUser.getLastKey())))
            .andExpect(jsonPath("$.lastKeyUpdateTime", is(controllerUser.getLastKeyUpdateTime().toString())))
            .andExpect(jsonPath("$.regDate", is(controllerUser.getRegDate().toString())))
            .andExpect(jsonPath("$.viplevel", is(controllerUser.getVIPLevel())))
            .andExpect(jsonPath("$.npcountry", is(controllerUser.getNPCountry())))
            .andExpect(jsonPath("$.platformType", is(controllerUser.getPlatformType())))
            .andExpect(jsonPath("$.isDeleted", is(controllerUser.isDeleted())))
            .andExpect(jsonPath("$.secondaryDownload", is(controllerUser.hasSecondaryDownload())))
            .andDo(print())
            .andReturn();

        verify(userController, times(1)).getUser(25042L);
    }
    
}
