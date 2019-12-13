package com.example.demo;

import java.util.concurrent.atomic.AtomicLong;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    private Logger logger = LoggerFactory.getLogger(GreetingController.class);

    @Autowired
    @Qualifier("global")
    DataSource dataSource;

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        // Mono.fromCallable(() -> {
        //     return null;
        // }).subscribeOn(Schedulers.elastic()).log();

        // Connection con;
        // try {
        //     con = dataSource.getConnection();
        //     PreparedStatement pstmt = con.prepareStatement("select * from gb_char");
        //     ResultSet rs = pstmt.executeQuery();

        //     if (rs.next()) {
        //         logger.info(rs.getString(4));
        //     }
        //     con.close();
        // } catch (SQLException e) {
        //     e.printStackTrace();
        // }

      logger.info("greeting");
    return new Greeting(counter.incrementAndGet(),
              String.format(template, name));
  }
}